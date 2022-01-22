package onlineroomrent.dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class VerifyOTP {
    @NotBlank(message = "Please enter valid otp")
    @Size(max = 6,message = "Please enter otp at least 6 digits long")
    private String otp;
    @NotBlank(message = "Please enter valid email or mobile")
    private String emailOrMobile;
}

