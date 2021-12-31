package onlineroomrent.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError {
    private String fieldName;
    private String message;
    private Object rejectedValue;
}
