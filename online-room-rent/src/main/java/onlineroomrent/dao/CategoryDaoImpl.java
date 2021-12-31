package onlineroomrent.dao;

import onlineroomrent.dao.entity.PropertyCategoryEntity;
import onlineroomrent.dao.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao{
  @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void save(PropertyCategoryEntity category) {
     categoryRepository.save(category);
    }
}
