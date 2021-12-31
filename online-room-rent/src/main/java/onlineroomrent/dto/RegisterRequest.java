package onlineroomrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RegisterRequest {
@NotBlank(message = "Please enter valid first name")
    private String firstName;
    @NotBlank(message = "Please enter valid first name")
    private String lastName;
    @NotBlank(message = "Please enter valid mobile")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Please enter valid mobile number")
    private String mobile;
    @NotBlank(message = "Please enter valid email")
    @Email(message = "Please enter valid email")
    private String email;
    @NotNull(message = "Please enter valid password")
    private String password;
    @NotBlank(message = "Please enter valid confirm password")
    private String confirmPassword;
}
