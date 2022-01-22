package onlineroomrent.dao.repository;

import onlineroomrent.dao.entity.PropertyCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyCategoryRepository extends JpaRepository<PropertyCategoryEntity,Long> {
}
