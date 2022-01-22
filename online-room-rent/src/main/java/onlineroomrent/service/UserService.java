package onlineroomrent.service;
import onlineroomrent.dto.ApiResponse;

public interface UserService {
    ApiResponse findByEmailAndPassword(String email,String password);
}
