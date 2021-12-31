package onlineroomrent.dao.repository;

import onlineroomrent.dao.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<AdminEntity,Long> {

    @Query(value="select * from admin where email=?1 or mobile =?1",nativeQuery = true)
    AdminEntity findByEmailOrMobile(String email);

    @Query(value="select * from admin where (email=?1 or mobile =?1) and is_block=true",nativeQuery=true)
    AdminEntity  isAccountBlocked(String email);

    @Query(value="select * from admin where (email=?1 or mobile =?1) and password=?2 ",nativeQuery=true)
    AdminEntity findByEmailAndPassword(String email,String password);

    AdminEntity findByUserType(String userType);
}
