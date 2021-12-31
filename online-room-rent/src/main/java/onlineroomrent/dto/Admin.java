package onlineroomrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Getter
@Setter
public class Admin {
    @NotBlank(message = "Please enter valid name")
    private String name;
    @NotBlank(message = "Please enter valid email")
    @Email(message = "Please enter valid email id")
    private String description;
    @NotBlank(message = "Please enter valid password")
    private String slugName;
}
