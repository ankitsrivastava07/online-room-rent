package onlineroomrent.dao.repository;

import onlineroomrent.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query(name = "select * from users where (email or mobile)=?1 and password=?2",nativeQuery=true)
    UserEntity findByEmailAndPassword(String email, String password);

    UserEntity findByEmailOrMobile(String email,String mobile);
}
