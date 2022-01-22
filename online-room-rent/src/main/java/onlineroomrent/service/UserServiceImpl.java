package onlineroomrent.service;
import onlineroomrent.dao.UserDao;
import onlineroomrent.dao.entity.UserEntity;
import onlineroomrent.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public ApiResponse findByEmailAndPassword(String email, String password) {
        UserEntity userEntity= userDao.findByEmailAndPassword(email,password);
        ApiResponse apiResponse = new ApiResponse();
        if(userEntity==null){
            apiResponse.setMessage("Invalid email/mobile or password");
            apiResponse.setStatus(Boolean.FALSE);
        }
         return apiResponse;
    }
}
