package onlineroomrent.dao.repository;

import onlineroomrent.dao.entity.PropertyCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<PropertyCategoryEntity,Long> {
}
