package onlineroomrent.exceptionHandle.exception;

public class AccountBlockedException extends RuntimeException{

    public AccountBlockedException(String msg){
        super(msg);
    }
}
