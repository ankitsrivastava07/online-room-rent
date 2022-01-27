package onlineroomrent.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private String message;
    private Boolean status;
    private String accessToken;
    private String redirectUri;
    private Boolean isValidFile;
    @JsonIgnore
    private String role;
}
