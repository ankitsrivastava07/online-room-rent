package onlineroomrent.dao.repository;
import onlineroomrent.dao.entity.country.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CountryRepository extends JpaRepository<CountryEntity,Long> {
}
