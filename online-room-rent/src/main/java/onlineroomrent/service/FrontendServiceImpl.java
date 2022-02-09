package onlineroomrent.service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import onlineroomrent.constant.OnlineRoomRentConstant;
import onlineroomrent.convertor.DtoToEntityConvertor;
import onlineroomrent.dao.DaoService;
import onlineroomrent.dao.entity.*;
import onlineroomrent.dao.repository.*;
import onlineroomrent.dateUtil.DateUtil;
import onlineroomrent.dto.*;
import onlineroomrent.exceptionHandle.exception.*;
import onlineroomrent.jwtUtil.JwtAccessTokenUtil;
import onlineroomrent.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
@Service
public class FrontendServiceImpl implements FrontendService {
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
    UserRepository userRepository;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    AmazonS3 amazonS3;
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Autowired
    private CountryRepository countryRepository;
    private final Integer MAX_OTP_LIMITED = 3;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired PropertyAdsRepository propertyAdsRepository;
    private List<String>fileExtension= new ArrayList<>();
    private Integer fileSize=5242880;
    @Autowired
    PropertyAdsRepository adsRepository;

    public FrontendServiceImpl(){
        fileExtension.add("image/jpg");
        fileExtension.add("image/jpeg");
        fileExtension.add("image/png");
        fileExtension.add("image/gif");
    }

    public Integer saveFailedAttempt(String emailOrMobile) {
        AdminEntity adminEntity = daoService.getUser(emailOrMobile);
        adminEntity.setFailedAttempt(adminEntity.getFailedAttempt() - 1);
        AdminEntity admin = daoService.saveAdmin(adminEntity);
        Integer attempt = admin.getFailedAttempt();
        if (admin.getFailedAttempt() <= 0) {
            adminEntity.setIsBlock(Boolean.TRUE);
            Date date = DateUtil.addDay();
            adminEntity.setNonBlockDate(date);
            daoService.saveAdmin(adminEntity);
            return attempt;
        }
        return attempt;
    }

    public AdminEntity saveMaxAttempts(String emailOrMobile) {
        AdminEntity adminEntity = daoService.getUser(emailOrMobile);
        adminEntity.setMaxAttempt(adminEntity.getMaxAttempt() - 1);
        AdminEntity admin = daoService.saveAdmin(adminEntity);
        return adminEntity;
    }

    public boolean isAccountExist(String emailOrMobile) {
        if (Objects.isNull(daoService.isAccountExist(emailOrMobile)))
            throw new UserNameNotFoundException("Email/mobile does not exist");
        return true;
    }

    public void isAccountBlocked(String emailOrMobile) {
        if (isAccountExist(emailOrMobile) && daoService.isAccountBlocked(emailOrMobile) != null)
            throw new AccountBlockedException("Account has been blocked for 24 hours");
    }

    @Override
    public ApiResponse adminLogin(String emailOrMobile, String password) {
        isAccountBlocked(emailOrMobile);
        ApiResponse apiResponse = new ApiResponse();
        AdminEntity adminEntity = daoService.login(emailOrMobile, password);
        if (adminEntity != null) {
            adminEntity.setFailedAttempt(4);
            adminEntity.setMaxAttempt(3);
            daoService.saveAdmin(adminEntity);
            apiResponse.setStatus(Boolean.TRUE);
            String jwt = createAccessToken(adminEntity);
            String identity = getTokenIdentity(jwt, "identity");
            apiResponse.setAccessToken(jwt);
            apiResponse.setMessage("Successfully logined");
            apiResponse.setRole(adminEntity.getUserType());
            saveToken(OnlineRoomRentConstant.Admin_TOKEN_KEY, jwt);
            return apiResponse;
        } else if (Objects.isNull(adminEntity) && daoService.getUser(emailOrMobile).getMaxAttempt() > 0 && saveMaxAttempts(emailOrMobile) != null) {
            apiResponse.setMessage("Invalid email/mobile or password");
            apiResponse.setStatus(Boolean.FALSE);
            return apiResponse;
        }
        Integer attempt = saveFailedAttempt(emailOrMobile);
        apiResponse.setMessage("Allowed maximum attempts before account getting block " + attempt);
        apiResponse.setStatus(Boolean.FALSE);
        return apiResponse;
    }

    @Override
    @Transactional
    public ApiResponse registerOwner(RegisterRequest registerRequest) {
        UserEntity entity = null;
        if ((entity = daoService.findByEmailOrMobile(registerRequest.getEmail(), null)) != null)
            throw new EmailAlreadyExistException("Some one already taken this email", entity.getEmail());
        else if ((entity = daoService.findByEmailOrMobile(null, registerRequest.getMobile())) != null)
            throw new MobileAlreadyExist("Mobile number already registered", entity.getMobile());
        UserEntity userEntity = dtoToEntityConvertor.convertToEntity(registerRequest, UserEntity.class);
        Role role=userRoleRepository.findByName(registerRequest.getRole());
        userEntity.setCreatedBy(role.getAdminEntity().getId());
        userEntity.setRole(role);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(Boolean.TRUE);
        apiResponse.setMessage("Successfully registered owner");
        userEntity.setUserType(OnlineRoomRentConstant.PROPERTY_OWNER);
        UserEntity userEntity1 = daoService.registerUser(userEntity);
        String jwt = createAccessToken(userEntity1);
        String identity = jwtAccessTokenUtil.getPrincipalFromToken(jwt, "identity");
        saveToken(OnlineRoomRentConstant.User_TOKEN_KEY, jwt);
        apiResponse.setAccessToken(jwt);
        apiResponse.setRedirectUri("/add-property");
        return apiResponse;
    }

    @Override
    public ApiResponse saveAdmin(Admin admin) {
        return null;
    }

    public ApiResponse updateCategory(CategoryDto categoryDto) {
        TokenStatus tokenStatus = TenantContext.getCurrentTenant();
        Long userId = jwtAccessTokenUtil.getUserId(tokenStatus.getAccessToken());
        PropertyCategoryEntity category = dtoToEntityConvertor.convertToEntity(categoryDto, PropertyCategoryEntity.class);
        category.setUserType(OnlineRoomRentConstant.SYSTEM_ADMIN);
        category.setAdmin(adminRepository.findById(userId).get());
        category = propertyCategoryRepository.save(category);
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
    public boolean isValidToken(String jwt, String key) {
        if (jwt == null || jwt.equals("undefined") || jwt.equals("null"))
            return false;
        String identity = key+"_"+getTokenIdentity(jwt, "identity");
        String token = getToken(identity);
        return token != null;
    }

    public String getTokenIdentity(String accessToken, String value) {
        try {
            if(accessToken.isEmpty())
                return "";
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String[] parts = accessToken.split("\\."); // Splitting header, payload and signature
            String payload = new String(decoder.decode(parts[1]));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(payload, Map.class);
            return String.valueOf(map.get(value));
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void invalidateToken(String jwt, String key) {
        TokenStatus tokenStatus = TenantContext.getCurrentTenant();
        tokenStatus.setStatus(Boolean.FALSE);
        TenantContext.setTenantContext(tokenStatus);
        String identity =getTokenIdentity(jwt, "identity");
        JwtTokenEntity jwtTokenEntity = jwtTokenRepository.findByTokenIdentity(identity);
        identity= key+"_"+getTokenIdentity(jwt, "identity");
        //jwtTokenEntity.setActive(Boolean.FALSE);
        boolean flag=redisTemplate.delete(identity);
        //jwtTokenEntity=jwtTokenRepository.save(jwtTokenEntity);
        String token=redisTemplate.opsForValue().get(identity);
    }

    @Override
    public List<PropertyCategoryEntity> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Role> findAllRole() {
        return daoService.findAllRole();
    }

    @Override
    public ApiResponse verfiyOtp(VerifyOTP verifyOTP) {
        String value = saveOTP(verifyOTP.getEmailOrMobile(), verifyOTP.getOtp());
        ApiResponse apiResponse = new ApiResponse();
        if (value == null) {
            apiResponse.setStatus(Boolean.FALSE);
            apiResponse.setMessage("Invalid otp");
            return apiResponse;
        }
        return null;
    }

    @Override
    public String findById(String jwt) {
        String identityToken = getTokenIdentity(jwt, "identity");
        JwtTokenEntity entity = jwtTokenRepository.findByTokenIdentity(identityToken);
        return entity != null ? entity.getUserName() : null;
    }

    @Override
    public long findByUserId(String jwt) {
        String identityToken = getTokenIdentity(jwt, "identity");
        JwtTokenEntity entity = jwtTokenRepository.findByTokenIdentity(identityToken);
        return entity.getUserId();
    }

    public String createAccessToken(Object object) {
        if (object instanceof AdminEntity) {
            AdminEntity entity = null;
            entity = (AdminEntity) object;
            JwtTokenEntity jwtTokenEntity = new JwtTokenEntity();
            String jwt = jwtAccessTokenUtil.createAccessToken(entity.getId(), entity.getUserType(), entity.getFirstName());
            jwtTokenEntity.setAccessToken(jwt);
            jwtTokenEntity.setUserType(jwtAccessTokenUtil.getPrincipalFromToken(jwt, entity.getUserType()));
            jwtTokenEntity.setTokenIdentity(jwtAccessTokenUtil.getPrincipalFromToken(jwt, "identity"));
            jwtTokenEntity.setUserId(entity.getId());
            jwtTokenEntity.setActive(Boolean.TRUE);
            jwtTokenEntity.setExpireAt(DateUtil.addMintues(OnlineRoomRentConstant.JWT_SESSION_EXPIRED));
            jwtTokenEntity.setUserName(entity.getFirstName());
            jwtTokenEntity = jwtTokenRepository.save(jwtTokenEntity);
            return jwtTokenEntity.getAccessToken();
        } else if (object instanceof UserEntity) {
            UserEntity entity = null;
            entity = (UserEntity) object;
            JwtTokenEntity jwtTokenEntity = new JwtTokenEntity();
            String jwt = jwtAccessTokenUtil.createAccessToken(entity.getId(), entity.getUserType(), entity.getFirstName());
            jwtTokenEntity.setAccessToken(jwt);
            jwtTokenEntity.setUserType(jwtAccessTokenUtil.getPrincipalFromToken(jwt, entity.getUserType()));
            jwtTokenEntity.setTokenIdentity(jwtAccessTokenUtil.getPrincipalFromToken(jwt, "identity"));
            jwtTokenEntity.setUserId(entity.getId());
            jwtTokenEntity.setActive(Boolean.TRUE);
            jwtTokenEntity.setExpireAt(DateUtil.addMintues(OnlineRoomRentConstant.JWT_SESSION_EXPIRED));
            jwtTokenEntity.setUserName(entity.getFirstName());
            jwtTokenEntity = jwtTokenRepository.save(jwtTokenEntity);
            return jwtTokenEntity.getAccessToken();
        } else if (object instanceof UserEntity) {
            UserEntity entity = null;
            entity = (UserEntity) object;
            JwtTokenEntity jwtTokenEntity = new JwtTokenEntity();
            String jwt = jwtAccessTokenUtil.createAccessToken(entity.getId(), entity.getUserType(), entity.getFirstName());
            jwtTokenEntity.setAccessToken(jwt);
            jwtTokenEntity.setUserType(jwtAccessTokenUtil.getPrincipalFromToken(jwt, entity.getUserType()));
            jwtTokenEntity.setTokenIdentity(jwtAccessTokenUtil.getPrincipalFromToken(jwt, "identity"));
            jwtTokenEntity.setUserId(entity.getId());
            jwtTokenEntity.setActive(Boolean.TRUE);
            jwtTokenEntity.setExpireAt(DateUtil.addMintues(OnlineRoomRentConstant.JWT_SESSION_EXPIRED));
            jwtTokenEntity.setUserName(entity.getFirstName());
            jwtTokenEntity = jwtTokenRepository.save(jwtTokenEntity);
            return jwtTokenEntity.getAccessToken();
        }
        return null;
    }

    public String saveToken(String key, String value) {
        String tokenIdentity=key+"_"+getTokenIdentity(value,"identity");
        redisTemplate.opsForValue().set(tokenIdentity, value, 30, TimeUnit.MINUTES);
        String hv = redisTemplate.opsForValue().get(key);
        return hv;
    }

    public String getToken(String key) {
        String jwt = redisTemplate.opsForValue().get(key);
        return jwt;
    }

    public String saveOTP(String key, String value) {
        String hv2 = redisTemplate.opsForValue().get(key + ":" + value);
        redisTemplate.opsForValue().set(key + ":" + value, value, 1, TimeUnit.MINUTES);
        String hv = redisTemplate.opsForValue().get(key + ":" + value);
        isOtpMaxLimitExceed(key, value);
        return hv;
    }

    public void isOtpMaxLimitExceed(String key, String value) {
        if (Long.valueOf(redisTemplate.opsForValue().get(key)) >= MAX_OTP_LIMITED) {
            throw new OTPMaxLimitExceedException("You have reached the maximum allowed limits,please retry after 10 minutes");
        }
        if (redisTemplate.opsForValue().get(key) == null)
            redisTemplate.opsForValue().set(key, "1", 5, TimeUnit.MINUTES);
        else
            redisTemplate.opsForValue().set(key, String.valueOf(Integer.valueOf(redisTemplate.opsForValue().get(key)) + 1), 10, TimeUnit.MINUTES);
    }

    @Override
    @Transactional
    public ApiResponse saveProperty(PostProperty postProperty) {
       TokenStatus tokenStatus = TenantContext.getCurrentTenant();
        ApiResponse apiResponse=null;
        if(!(apiResponse=isValidFileFormat(postProperty.getImage1())).getIsValidFile() || !(apiResponse=isValidFileFormat(postProperty.getImage2())).getIsValidFile()){
            return apiResponse;
        }
        PropertyAdsEntity entity = dtoToEntityConvertor.convertToEntity(postProperty,PropertyAdsEntity.class);
        String propertyTitle=postProperty.getPropertyTitle();
        entity.setSlugName(propertyTitle.replace(' ','-'));
        PropertyCategoryEntity entity1=propertyCategoryRepository.findById(postProperty.getProductCategory()).get();
        entity.setCategoryEntity(entity1);
        entity.setUser(userRepository.getById(tokenStatus.getUserId()));
        AddressEntity address = new AddressEntity();
        address.setCity(cityRepository.getById(postProperty.getCity()));
        address.setState(stateRepository.getById(postProperty.getState()));
        address.setCountry(countryRepository.getById(postProperty.getCountry()));
        address.setAddress(postProperty.getAddress());
        address.setSlugName(postProperty.getAddress().replace(' ','-')+","+stateRepository.getById(postProperty.getState()).getState());
        entity.setAddress(address);
        entity= propertyAdsRepository.save(entity);
        saveImage(postProperty.getImage1(),entity);
        saveImage(postProperty.getImage2(),entity);
        apiResponse.setMessage("Successfully saved record");
        apiResponse.setStatus(Boolean.TRUE);
        //apiResponse.se
        return apiResponse;
    }

    private ApiResponse isValidFileFormat(MultipartFile image1) {
        ApiResponse apiResponse = new ApiResponse();
        for(String fileFormat:fileExtension){
           if(fileFormat.equalsIgnoreCase(image1.getContentType())){
               apiResponse.setIsValidFile(Boolean.TRUE);
               return apiResponse;
            }
        }
        apiResponse.setStatus(Boolean.FALSE);
        apiResponse.setMessage("Invalid file saint , Allowed files are jpeg,jpg,png,svg 5MB size");
        apiResponse.setIsValidFile(Boolean.FALSE);
        return apiResponse;
    }

    @Override
    public List<PropertyAdsDto> findAllPropertyAds() {
        List<PropertyAdsEntity> entity=adsRepository.findAll();
        List<PropertyAdsDto>dtos=dtoToEntityConvertor.convertToDto(entity,PropertyAdsDto.class);
        return dtos;
    }

    @Override
    public List<PropertyAdsDto> findAllPropertyAdsBySlugAndAdrress(String slugName, String address, String state) {
        List<PropertyAdsEntity> entity=adsRepository.findAllPropertyBySlugAndAddress(slugName,address,state);
        List<PropertyAdsDto>dtos=dtoToEntityConvertor.convertToDto(entity,PropertyAdsDto.class);
        return dtos;
    }

    public PropertyAdsEntity saveImage(MultipartFile image,PropertyAdsEntity adsEntity) {
        try {
            String imageName=image.getOriginalFilename();
            PropertyImages propertyImages1 = new PropertyImages();
            propertyImages1.setContentType(image.getContentType());
            propertyImages1.setFileName(image.getOriginalFilename());
            propertyImages1.setBucketName(this.bucketName+'/'+OnlineRoomRentConstant.AWS_BUCKET_NAME);
            propertyImages1.setPropertyAds(adsEntity);
            File file = new File(imageName);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(image.getBytes());
            AmazonS3Client amazonS3Client=(AmazonS3Client)amazonS3;
            amazonS3Client.putObject(new PutObjectRequest(bucketName+'/'+OnlineRoomRentConstant.AWS_BUCKET_NAME,imageName,file).withCannedAcl(CannedAccessControlList.PublicRead));
            S3Object s3Object = amazonS3.getObject(bucketName+'/'+OnlineRoomRentConstant.AWS_BUCKET_NAME, imageName);
            String fileAccessUrl="https://online-room-rent.s3.ap-south-1.amazonaws.com/images/"+imageName;
            propertyImages1.setBucketUrl(fileAccessUrl);
            if(adsEntity.getImages()==null) {
                List<PropertyImages>list = new ArrayList<>();
                list.add(propertyImages1);
                adsEntity.setImages(list);
            }
            else{
                List<PropertyImages> list=adsEntity.getImages();
                list.add(propertyImages1);
                adsEntity.setImages(list);
            }
            file.delete();
        } catch (AmazonS3Exception | IOException amazonS3Exception) {
            amazonS3Exception.printStackTrace();
        }
        return adsEntity;
    }
}
