package onlineroomrent.dao.repository;
import onlineroomrent.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String role);
    @Query(value = "select * from role where name!=?1",nativeQuery = true)
    List<Role> findUsersRole(String admin);
}
