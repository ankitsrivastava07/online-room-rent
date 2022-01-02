package onlineroomrent.service;

import onlineroomrent.dao.entity.JwtTokenEntity;
import onlineroomrent.dao.entity.PropertyCategoryEntity;
import onlineroomrent.dao.entity.Role;
import onlineroomrent.dto.*;

import java.util.List;

public interface FrontendService {

    ApiResponse registerOwner(RegisterRequest registerRequest);

    ApiResponse saveAdmin(Admin admin);

    ApiResponse login(String emailOrMobile,String password);

    ApiResponse updateRole(RoleDto roleDto);

    ApiResponse updateCategory(CategoryDto categoryDto);

    void saveRole(Role role);

    JwtTokenEntity isValidToken(String jwt);

    void invalidateToken(String jwt);

    List<PropertyCategoryEntity> findAllCategories();
}
