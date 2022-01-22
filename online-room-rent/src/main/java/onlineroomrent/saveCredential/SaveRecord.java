/*
package onlineroomrent.saveCredential;

import onlineroomrent.constant.OnlineRoomRentConstant;
import onlineroomrent.dao.entity.*;
import onlineroomrent.dao.repository.AdminRepository;
import onlineroomrent.encryptUtil.EncryptUtil;
import onlineroomrent.service.CategoryService;
import onlineroomrent.service.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
@Component
public class SaveRecord implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    EncryptUtil encryptUtil;

    @Autowired
    CategoryService categoryService;

    @Autowired
    FrontendService frontendService;

    @Override
    public void run(String... args) throws Exception {
        AdminEntity adminEntity = new AdminEntity();
        UserGrantedAuthority authority = new UserGrantedAuthority();
        Role role = new Role();
        adminEntity.setFirstName("Ankit");
        adminEntity.setLastName("Srivastava");
        adminEntity.setMaxAttempt(3);
        adminEntity.setUserType(OnlineRoomRentConstant.SYSTEM_ADMIN);
        adminEntity.setActive(Boolean.TRUE);
        adminEntity.setEmail("ankitsrivastava7835@gmail.com");
        adminEntity.setMobile("9990545169");
        adminEntity.setUserType(OnlineRoomRentConstant.SYSTEM_ADMIN);
        String encryptedString=encryptUtil.encrypt("Ankit@123");
        String decpPass=encryptUtil.decrypt(encryptedString);
        adminEntity.setPassword(encryptedString);
        role.setUserType(OnlineRoomRentConstant.SYSTEM_ADMIN);
        role.setActive(Boolean.TRUE);
        role.setDescription("Admin have all access rights");
        role.setAdminEntity(adminEntity);
        authority.setActive(Boolean.TRUE);
        authority.setDescription("Admin have all access rights");
        authority.setAdminEntity(adminEntity);
        authority.setRole(role);
        role.setAuthorities(Arrays.asList(authority));
        adminEntity.setRole(role);
      //  AdminEntity entity =adminRepository.save(adminEntity);
     //   saveCategory(entity);
      //  saveUserRole(adminEntity);
    }

}*/
