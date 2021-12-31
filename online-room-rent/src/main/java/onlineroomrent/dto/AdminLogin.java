package onlineroomrent.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AdminLogin {
    @NotBlank(message = "Please enter valid email or mobile")
    private String emailOrMobile;
    @NotBlank(message = "Please enter valid password")
    private String password;
}
