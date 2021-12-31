package onlineroomrent.service;

import onlineroomrent.dao.CategoryDao;
import onlineroomrent.dao.entity.PropertyCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImpl implements CategoryService{

    @Autowired
    CategoryDao categoryDao;

    @Override
    public void saveCategory(PropertyCategoryEntity category) {
        categoryDao.save(category);
    }
}
