package onlineroomrent.service;
import onlineroomrent.dao.entity.PropertyCategoryEntity;
import onlineroomrent.dao.entity.Role;
import onlineroomrent.dto.*;
import java.util.List;
public interface FrontendService {

    ApiResponse registerOwner(RegisterRequest registerRequest);

    ApiResponse saveAdmin(Admin admin);

    ApiResponse adminLogin(String emailOrMobile, String password);

    ApiResponse updateRole(RoleDto roleDto);

    ApiResponse updateCategory(CategoryDto categoryDto);

    void saveRole(Role role);

    boolean isValidToken(String jwt, String key);

    void invalidateToken(String jwt, String key);

    List<PropertyCategoryEntity> findAllCategories();

    List<Role> findAllRole();

    ApiResponse verfiyOtp(VerifyOTP verifyOTP);

    abstract String findById(String jwt);

    long findByUserId(String jwt);

    ApiResponse saveProperty(PostProperty postProperty);

    List<PropertyAdsDto> findAllPropertyAds();

    List<PropertyAdsDto> findAllPropertyAdsBySlugAndAdrress(String slugName, String address, String state);
}
