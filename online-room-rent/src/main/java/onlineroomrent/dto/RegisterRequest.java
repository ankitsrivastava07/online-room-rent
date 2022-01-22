package onlineroomrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

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
    @Size(min=6,max = 30,message = "Please enter password atleast 6 characters and maximum 30 characters long")
    private String password;
    @NotBlank(message = "Please enter valid confirm password")
    @Size(min=6,max = 30,message = "Please enter Confirm password atleast 6 characters and maximum 30 characters long")
    private String confirmPassword;
    @NotNull(message = "Please enter valid role id")
    private Long roleId;
}
