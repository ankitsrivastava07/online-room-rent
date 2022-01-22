package onlineroomrent.dao.repository;

import onlineroomrent.dao.entity.country.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity,Long> {
}
