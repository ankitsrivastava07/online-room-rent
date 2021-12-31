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
        AdminEntity entity =adminRepository.save(adminEntity);
        saveCategory(entity);
        saveUserRole(adminEntity);
    }

    public void saveCategory(AdminEntity admin){
        PropertyCategoryEntity entity1= new PropertyCategoryEntity();
        entity1.setCategoryName("ALL RESIDENTIAL");
        entity1.setDescription("All residential");
        entity1.setSlugName("ALL_RESIDENTIAL");
        entity1.setAdmin(admin);
        categoryService.saveCategory(entity1);

        PropertyCategoryEntity entity2= new PropertyCategoryEntity();
        entity2.setCategoryName("Flat/Apartment");
        entity2.setDescription("Flat/ Apartment");
        entity2.setSlugName("Flat/Apartment");
        entity2.setAdmin(admin);
        categoryService.saveCategory(entity2);

        PropertyCategoryEntity entity3= new PropertyCategoryEntity();
        entity3.setCategoryName("Villa");
        entity3.setDescription("Villa");
        entity3.setSlugName("Villa");
        entity3.setAdmin(admin);
        categoryService.saveCategory(entity3);

        PropertyCategoryEntity entity4= new PropertyCategoryEntity();
        entity4.setCategoryName("Builder Floor Apartment");
        entity4.setDescription("Builder Floor Apartment");
        entity4.setSlugName("Builder_Floor_Apartment");
        entity4.setAdmin(admin);
        categoryService.saveCategory(entity4);

        PropertyCategoryEntity entity5= new PropertyCategoryEntity();
        entity5.setCategoryName("Residential Land/ Plot");
        entity5.setDescription("Residential Land/ Plot");
        entity5.setSlugName("Residential_Land/Plot");
        entity5.setAdmin(admin);
        categoryService.saveCategory(entity5);

        PropertyCategoryEntity entity6= new PropertyCategoryEntity();
        entity6.setCategoryName("Commercial Office Space");
        entity6.setDescription("Commercial Office Space");
        entity6.setSlugName("Commercial_Office_Space");
        entity6.setAdmin(admin);
        categoryService.saveCategory(entity6);

        PropertyCategoryEntity entity7= new PropertyCategoryEntity();
        entity7.setCategoryName("Commercial Showroom");
        entity7.setDescription("Commercial Showroom");
        entity7.setSlugName("Commercial_Showroom");
        entity7.setAdmin(admin);
        categoryService.saveCategory(entity7);

        PropertyCategoryEntity entity8= new PropertyCategoryEntity();
        entity8.setCategoryName("Commercial Land");
        entity8.setDescription("Commercial Land");
        entity8.setSlugName("Commercial_Land");
        entity8.setAdmin(admin);
        categoryService.saveCategory(entity8);
    }

    public void saveUserRole(AdminEntity entity){
        UserGrantedAuthority grantedAuthority = new UserGrantedAuthority();
        Role role1= new Role();
        role1.setActive(Boolean.TRUE);
        role1.setAdminEntity(entity);
        role1.setDescription("Property owner");
        role1.setUserType(OnlineRoomRentConstant.PROPERTY_OWNER);
        grantedAuthority.setRole(role1);
        grantedAuthority.setAccessRights("/api/v1/property");
        grantedAuthority.setDescription("Property owner can access all records of property");
        grantedAuthority.setRole(role1);
        grantedAuthority.setDescription("Property owner");
        role1.setAuthorities(Arrays.asList(grantedAuthority));
        frontendService.saveRole(role1);
        UserGrantedAuthority grantedAuthority2 = new UserGrantedAuthority();
        Role role2= new Role();
        role2.setActive(Boolean.TRUE);
        role2.setAdminEntity(entity);
        role2.setUserType(OnlineRoomRentConstant.USER);
        role2.setDescription("Properyt owner");
        grantedAuthority2.setRole(role2);
        grantedAuthority2.setDescription("User");
        grantedAuthority2.setAccessRights("/api/v1/user");
        grantedAuthority2.setDescription("Property owner can access all records of property");
        grantedAuthority2.setRole(role2);
        role2.setAuthorities(Arrays.asList(grantedAuthority2));
        frontendService.saveRole(role2);
    }

}