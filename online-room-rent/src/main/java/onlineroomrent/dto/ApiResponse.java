package onlineroomrent.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import onlineroomrent.validation.ValidationError;

import java.util.List;

@Getter
@Setter
public class ApiResponse {
    private String message;
    private Boolean status;
    private String accessToken;
    private String redirectUri;
    @JsonIgnore
    private String role;
}
