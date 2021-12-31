package onlineroomrent.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;
@Component
public class ValidationUtil {

    public List<ValidationError> getAllErrors(List<FieldError>errors){
        List<ValidationError> validationErrors= new ArrayList<>();
        errors.stream().forEach(error->{
            ValidationError validationError= new ValidationError();
            validationError.setFieldName(error.getField());
            validationError.setMessage(error.getDefaultMessage());
            validationError.setRejectedValue(error.getRejectedValue());
            validationErrors.add(validationError);
        });
        return validationErrors;
    }
}
