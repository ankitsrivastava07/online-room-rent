package onlineroomrent.dao.repository;
import onlineroomrent.dao.entity.country.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface StateRepository extends JpaRepository<StateEntity,Long> {
}
