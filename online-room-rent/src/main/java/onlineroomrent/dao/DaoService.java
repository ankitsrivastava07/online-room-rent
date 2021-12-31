package onlineroomrent.dao;

import onlineroomrent.dao.entity.AdminEntity;
import onlineroomrent.dao.entity.Role;
import onlineroomrent.dao.entity.UserEntity;

import java.util.List;

public interface DaoService {

    AdminEntity saveAdmin(AdminEntity adminEntity);

    AdminEntity login(String email,String password);

    AdminEntity isAccountExist(String emailOrMobile);

    AdminEntity isAccountBlocked(String emailOrMobile);

    AdminEntity getUser(String email);

    List<AdminEntity> findAll();

    void saveRole(Role role);

    UserEntity registerUser(UserEntity userEntity);

    UserEntity findByEmailOrMobile(String email,String mobile);
}
