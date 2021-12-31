package onlineroomrent.service;

import onlineroomrent.dao.entity.JwtTokenEntity;
import onlineroomrent.dao.entity.Role;
import onlineroomrent.dto.*;

public interface FrontendService {

    ApiResponse registerOwner(RegisterRequest registerRequest);

    ApiResponse saveAdmin(Admin admin);

    ApiResponse login(String emailOrMobile,String password);

    ApiResponse updateRole(RoleDto roleDto);

    ApiResponse updateCategory(CategoryDto categoryDto);

    void saveRole(Role role);

    JwtTokenEntity isValidToken(String jwt);

    void invalidateToken(String jwt);
}
