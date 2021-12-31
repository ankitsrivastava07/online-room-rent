package onlineroomrent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {

    private String name;
    private String description;
    private Boolean active;
    private Long adminId;
}
