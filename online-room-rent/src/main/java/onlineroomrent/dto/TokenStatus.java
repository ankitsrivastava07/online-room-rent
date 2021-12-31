package onlineroomrent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenStatus {
    private String accessToken;
    private String userName;
    private Boolean status=Boolean.FALSE;
}
