package onlineroomrent.dao.repository;

import onlineroomrent.dao.entity.country.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity,Long> {
}
