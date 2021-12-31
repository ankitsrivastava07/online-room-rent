package onlineroomrent.dao.repository;

import onlineroomrent.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmailOrMobile(String email, String mobile);
}
