package onlineroomrent.dao;

import onlineroomrent.dao.entity.AdminEntity;
import onlineroomrent.dao.entity.Role;
import onlineroomrent.dao.entity.UserEntity;
import onlineroomrent.dao.repository.AdminRepository;
import onlineroomrent.dao.repository.UserRepository;
import onlineroomrent.dao.repository.RoleRepository;
import onlineroomrent.encryptUtil.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoServiceImpl implements DaoService{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    RoleRepository userRoleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EncryptUtil encryptUtil;

    @Override
    public AdminEntity saveAdmin(AdminEntity adminEntity) {
        return adminRepository.save(adminEntity);
    }

    @Override
    public AdminEntity login(String email,String password) {
        String psdEnc=encryptUtil.encrypt(password);
        String dc=getUser(email).getPassword();
        AdminEntity entity=adminRepository.findByEmailAndPassword(email,psdEnc);
        return entity;
    }

    @Override
    public AdminEntity isAccountExist(String emailOrMobile) {
        return adminRepository.findByEmailOrMobile(emailOrMobile);
    }

    @Override
    public AdminEntity isAccountBlocked(String emailOrMobile) {
        return adminRepository.isAccountBlocked(emailOrMobile);
    }

    @Override
    public AdminEntity getUser(String email) {
        return adminRepository.findByEmailOrMobile(email);
    }

    @Override
    public List<AdminEntity> findAll(){
        return adminRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        userRoleRepository.save(role);
    }

    @Override
    public UserEntity registerUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public String findById(Long id) {
        String firstName=adminRepository.findById(id).get().getFirstName();
        return firstName;
    }

    @Override
    public UserEntity findByEmailOrMobile(String email, String mobile) {
        return userRepository.findByEmailOrMobile(email, mobile);
    }

    @Override
    public List<Role> findAllRole() {
        return userRoleRepository.findUsersRole("Admin");
    }
}
