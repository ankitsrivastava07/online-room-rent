package onlineroomrent.exceptionHandle.exception;

public class OTPMaxLimitExceedException extends RuntimeException {
    public OTPMaxLimitExceedException(String s) {
        super(s);
    }
}
