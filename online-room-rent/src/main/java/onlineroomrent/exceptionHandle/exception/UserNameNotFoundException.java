package onlineroomrent.exceptionHandle.exception;

public class UserNameNotFoundException extends RuntimeException{

    public UserNameNotFoundException(String msg){
        super(msg);
    }
}
