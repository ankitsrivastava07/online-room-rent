package onlineroomrent.dao;

import onlineroomrent.dao.entity.UserEntity;

public interface UserDao {
    UserEntity findByEmailAndPassword(String email,String password);
}
