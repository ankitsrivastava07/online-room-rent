package onlineroomrent.dao.repository;

import onlineroomrent.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByUserType(String userType);
}
