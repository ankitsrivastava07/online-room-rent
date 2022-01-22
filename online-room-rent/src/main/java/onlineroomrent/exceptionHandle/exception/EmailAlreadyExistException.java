package onlineroomrent.exceptionHandle.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAlreadyExistException extends RuntimeException {
    private String email;
    private String message;
    public EmailAlreadyExistException(String msg,String email) {
        this.message=msg;
        this.email=email;
    }
}
