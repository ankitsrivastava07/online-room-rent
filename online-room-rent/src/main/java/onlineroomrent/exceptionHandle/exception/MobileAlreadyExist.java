package onlineroomrent.exceptionHandle.exception;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MobileAlreadyExist extends RuntimeException {
    private String mobile;
    private String message;
    public MobileAlreadyExist(String msg,String mobile) {
        this.message=msg;
        this.mobile=mobile;

    }
}
