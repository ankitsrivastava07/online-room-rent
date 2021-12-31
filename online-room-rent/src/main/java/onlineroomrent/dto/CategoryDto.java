package onlineroomrent.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryDto {
    private Boolean active;
    private Long adminId;
    @NotBlank(message = "Please enter valid category Name")
    private String categoryName;
    @NotBlank(message = "Please enter valid description")
    @Size(min = 10,max = 1000,message = "Please enter description at least 10 characters long and maximum 1000 characters")
    private String description;
    @NotBlank(message = "Please enter valid slug name")
    private String slugName;
}
