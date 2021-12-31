package onlineroomrent.validation;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ValidationFailed {
    private Integer errorCode;
    private String message;
    private Boolean validationFailed=Boolean.FALSE;
    private List<ValidationError> error;
}
