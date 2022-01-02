package onlineroomrent.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import onlineroomrent.constant.OnlineRoomRentConstant;
import onlineroomrent.convertor.DtoToEntityConvertor;
import onlineroomrent.dao.DaoService;
import onlineroomrent.dao.entity.*;
import onlineroomrent.dao.repository.*;
import onlineroomrent.dateUtil.DateUtil;
import onlineroomrent.dto.*;
import onlineroomrent.exceptionHandle.exception.AccountBlockedException;
import onlineroomrent.exceptionHandle.exception.EmailAlreadyExistException;
import onlineroomrent.exceptionHandle.exception.MobileAlreadyExist;
import onlineroomrent.exceptionHandle.exception.UserNameNotFoundException;
import onlineroomrent.jwtUtil.JwtAccessTokenUtil;
import onlineroomrent.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FrontendServiceImpl implements FrontendService{
    @Autowired
    DaoService daoService;
    @Autowired
    private RoleRepository userRoleRepository;
    @Autowired
    private DtoToEntityConvertor dtoToEntityConvertor;
    @Autowired
    JwtAccessTokenUtil jwtAccessTokenUtil;
    @Autowired
    JwtTokenRepository jwtTokenRepository;
    @Autowired
    PropertyCategoryRepository propertyCategoryRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CategoryRepository categoryRepository;

    public Integer saveFailedAttempt(String emailOrMobile){
        AdminEntity adminEntity =daoService.getUser(emailOrMobile);
        adminEntity.setFailedAttempt(adminEntity.getFailedAttempt()-1);
        AdminEntity admin= daoService.saveAdmin(adminEntity);
        Integer attempt=admin.getFailedAttempt();
        if(admin.getFailedAttempt()<=0){
            adminEntity.setIsBlock(Boolean.TRUE);
            Date date= DateUtil.addDay();
            adminEntity.setNonBlockDate(date);
            daoService.saveAdmin(adminEntity);
            return attempt;
        }
        return attempt;
    }

    public AdminEntity saveMaxAttempts(String emailOrMobile){
        AdminEntity adminEntity =daoService.getUser(emailOrMobile);
        adminEntity.setMaxAttempt(adminEntity.getMaxAttempt()-1);
        AdminEntity admin= daoService.saveAdmin(adminEntity);
        return adminEntity;
    }

    public boolean isAccountExist(String emailOrMobile){
       if(Objects.isNull(daoService.isAccountExist(emailOrMobile)))
           throw new UserNameNotFoundException("Email/mobile does not exist");
       return true;
    }

    public void isAccountBlocked(String emailOrMobile){
        if (isAccountExist(emailOrMobile) && daoService.isAccountBlocked(emailOrMobile)!=null)
        throw new AccountBlockedException("Account has been blocked for 24 hours");
    }

    public ApiResponse login(String emailOrMobile,String password){
        isAccountBlocked(emailOrMobile);
        ApiResponse apiResponse = new ApiResponse();
        AdminEntity adminEntity =daoService.login(emailOrMobile,password);
        if(adminEntity!=null){
            adminEntity.setFailedAttempt(4);
            adminEntity.setMaxAttempt(3);
            daoService.saveAdmin(adminEntity);
            apiResponse.setStatus(Boolean.TRUE);
            String jwt=createAccessToken(adminEntity);
            apiResponse.setAccessToken(jwt);
            apiResponse.setMessage("Successfully logined");
            apiResponse.setRole(adminEntity.getUserType());
            //redisTemplate.p(jwt);
            return apiResponse;
        }
        else if(Objects.isNull(adminEntity) && daoService.getUser(emailOrMobile).getMaxAttempt()>0 && saveMaxAttempts(emailOrMobile)!=null){
            apiResponse.setMessage("Invalid email/mobile or password");
            apiResponse.setStatus(Boolean.FALSE);
            return apiResponse;
        }
        Integer attempt=saveFailedAttempt(emailOrMobile);
        apiResponse.setMessage("Allowed maximum attempts before account getting block "+attempt);
        apiResponse.setStatus(Boolean.FALSE);
       return apiResponse;
    }

    @Override
    public ApiResponse registerOwner(RegisterRequest registerRequest){
        UserEntity entity=daoService.findByEmailOrMobile(registerRequest.getEmail(),null);
        if(entity!=null)
            throw new EmailAlreadyExistException("Some one already taken this email");
        else if((entity=daoService.findByEmailOrMobile(registerRequest.getMobile(),null))!=null)
            throw new MobileAlreadyExist("Mobile number already registered");
        Role role=userRoleRepository.findByUserType(OnlineRoomRentConstant.PROPERTY_OWNER);
        UserEntity userEntity=dtoToEntityConvertor.convertToEntity(registerRequest, UserEntity.class);
        userEntity.setCreatedBy(role.getAdminEntity().getId());
        userEntity.setRole(role);
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setStatus(Boolean.TRUE);
        apiResponse.setMessage("Successfully registered owner");
        userEntity.setUserType(OnlineRoomRentConstant.PROPERTY_OWNER);
        UserEntity userEntity1 =daoService.registerUser(userEntity);
        String jwt=createAccessToken(userEntity1);
        apiResponse.setAccessToken(jwt);
        apiResponse.setRedirectUri("/add-property");
        return apiResponse;
    }

    @Override
    public ApiResponse saveAdmin(Admin admin) {
        return null;
    }

    public ApiResponse updateCategory(CategoryDto categoryDto){
      TokenStatus tokenStatus = TenantContext.getCurrentTenant();
       Long userId=jwtAccessTokenUtil.getUserId(tokenStatus.getAccessToken());
       PropertyCategoryEntity category =dtoToEntityConvertor.convertToEntity(categoryDto, PropertyCategoryEntity.class);
       category.setUserType(OnlineRoomRentConstant.SYSTEM_ADMIN);
       category.setAdmin(adminRepository.findById(userId).get());
       category=propertyCategoryRepository.save(category);
       ApiResponse apiResponse = new ApiResponse();
       apiResponse.setMessage("Successfully record saved");
       apiResponse.setStatus(Boolean.TRUE);
       return apiResponse;
}

    @Override
    public ApiResponse updateRole(RoleDto roleDto) {
       ApiResponse apiResponse = new ApiResponse();
       apiResponse.setMessage("Record successfully updated");
       apiResponse.setStatus(Boolean.TRUE);
       return apiResponse;
    }

    @Override
    public void saveRole(Role role) {
        daoService.saveRole(role);
    }

    @Override
    public JwtTokenEntity isValidToken(String jwt){
        if(jwt==null || jwt.equals("undefined") || jwt.equals("null"))
            return null;
       String identity=getTokenIdentity(jwt,"identity");
       JwtTokenEntity jwtTokenEntity=jwtTokenRepository.findByTokenIdentity(identity);
       if(jwtTokenEntity!=null && !jwtTokenEntity.getExpireAt().before(new Date()) && jwtTokenEntity.getActive())
           return jwtTokenEntity;
        return null;
    }

    public String getTokenIdentity(String accessToken,String value){
        try {
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String[] parts = accessToken.split("\\."); // Splitting header, payload and signature
            String payload = new String(decoder.decode(parts[1]));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(payload, Map.class);
            return String.valueOf(map.get(value));
        }catch (JsonProcessingException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void invalidateToken(String jwt){
        String identity=getTokenIdentity(jwt,"identity");
        JwtTokenEntity jwtTokenEntity=jwtTokenRepository.findByTokenIdentity(identity);
        jwtTokenEntity.setActive(Boolean.FALSE);
        jwtTokenRepository.save(jwtTokenEntity);
    }

    @Override
    public List<PropertyCategoryEntity> findAllCategories() {
          return categoryRepository.findAll();
    }

    public String createAccessToken(Object object){
        if(object instanceof AdminEntity){
            AdminEntity entity=null;
            entity=(AdminEntity)object;
            JwtTokenEntity jwtTokenEntity= new JwtTokenEntity();
            String jwt=jwtAccessTokenUtil.createAccessToken(entity.getId(),entity.getUserType(),entity.getFirstName());
            jwtTokenEntity.setAccessToken(jwt);
            jwtTokenEntity.setUserType(jwtAccessTokenUtil.getPrincipalFromToken(jwt,entity.getUserType()));
            jwtTokenEntity.setTokenIdentity(jwtAccessTokenUtil.getPrincipalFromToken(jwt,"identity"));
            jwtTokenEntity.setUserId(entity.getId());
            jwtTokenEntity.setActive(Boolean.TRUE);
            jwtTokenEntity.setExpireAt(DateUtil.addMintues(OnlineRoomRentConstant.JWT_SESSION_EXPIRED));
            jwtTokenEntity.setUserName(entity.getFirstName());
            jwtTokenEntity=jwtTokenRepository.save(jwtTokenEntity);
            return jwtTokenEntity.getAccessToken();
        }

        else if(object instanceof UserEntity){
            UserEntity entity=null;
            entity=(UserEntity)object;
            JwtTokenEntity jwtTokenEntity= new JwtTokenEntity();
            String jwt=jwtAccessTokenUtil.createAccessToken(entity.getId(),entity.getUserType(),entity.getFirstName());
            jwtTokenEntity.setAccessToken(jwt);
            jwtTokenEntity.setUserType(jwtAccessTokenUtil.getPrincipalFromToken(jwt,entity.getUserType()));
            jwtTokenEntity.setTokenIdentity(jwtAccessTokenUtil.getPrincipalFromToken(jwt,"identity"));
            jwtTokenEntity.setUserId(entity.getId());
            jwtTokenEntity.setActive(Boolean.TRUE);
            jwtTokenEntity.setExpireAt(DateUtil.addMintues(OnlineRoomRentConstant.JWT_SESSION_EXPIRED));
            jwtTokenEntity.setUserName(entity.getFirstName());
            jwtTokenEntity=jwtTokenRepository.save(jwtTokenEntity);
            return jwtTokenEntity.getAccessToken();
        }
        else if(object instanceof UserEntity){
            UserEntity entity=null;
            entity=(UserEntity)object;
            JwtTokenEntity jwtTokenEntity= new JwtTokenEntity();
            String jwt=jwtAccessTokenUtil.createAccessToken(entity.getId(),entity.getUserType(),entity.getFirstName());
            jwtTokenEntity.setAccessToken(jwt);
            jwtTokenEntity.setUserType(jwtAccessTokenUtil.getPrincipalFromToken(jwt,entity.getUserType()));
            jwtTokenEntity.setTokenIdentity(jwtAccessTokenUtil.getPrincipalFromToken(jwt,"identity"));
            jwtTokenEntity.setUserId(entity.getId());
            jwtTokenEntity.setActive(Boolean.TRUE);
            jwtTokenEntity.setExpireAt(DateUtil.addMintues(OnlineRoomRentConstant.JWT_SESSION_EXPIRED));
            jwtTokenEntity.setUserName(entity.getFirstName());
            jwtTokenEntity=jwtTokenRepository.save(jwtTokenEntity);
            return jwtTokenEntity.getAccessToken();
        }
      return null;
    }
}
