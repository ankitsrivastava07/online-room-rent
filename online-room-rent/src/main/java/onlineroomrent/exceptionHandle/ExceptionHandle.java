package onlineroomrent.exceptionHandle;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import onlineroomrent.constant.OnlineRoomRentConstant;
import onlineroomrent.dto.ApiResponse;
import onlineroomrent.error.ApiError;
import onlineroomrent.exceptionHandle.exception.*;
import onlineroomrent.validation.ValidationError;
import onlineroomrent.validation.ValidationFailed;
import onlineroomrent.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
public class ExceptionHandle {
    @Autowired
    ValidationUtil validationUtil;

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> tokenException(ExpiredJwtException exception, HttpServletRequest request) {
        ApiError apiError = new ApiError(Boolean.FALSE,HttpStatus.UNAUTHORIZED.toString(), OnlineRoomRentConstant.SESSION_EXPIRED_DEFAULT_MESSAGE);
        String uri=request.getRequestURI();
        ModelAndView mv= new ModelAndView();
        mv.addObject("sessionExpired", OnlineRoomRentConstant.SESSION_EXPIRED_DEFAULT_MESSAGE);
        mv.setViewName("/login");
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(org.springframework.web.multipart.MaxUploadSizeExceededException.class)
    public ResponseEntity<?> maxUploadSizeExceedException(MaxUploadSizeExceededException exception, HttpServletRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(Boolean.FALSE);
        apiResponse.setMessage("Invalid file saint , Allowed file size is 5MB ");
        apiResponse.setIsValidFile(Boolean.FALSE);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({JwtException.class})
    public ResponseEntity<?> tokenException(JwtException exception) {
        ApiError apiError = new ApiError(Boolean.FALSE,HttpStatus.UNAUTHORIZED.toString(), exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationErrors(MethodArgumentNotValidException exception){
        ValidationFailed validationFailed = new ValidationFailed();
        validationFailed.setErrorCode(HttpStatus.BAD_REQUEST.value());
        validationFailed.setMessage("Validation Failed");
        validationFailed.setError(validationUtil.getAllErrors(exception.getFieldErrors()));
        validationFailed.setValidationFailed(Boolean.TRUE);
        return new ResponseEntity<>(validationFailed, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.validation.BindException.class)
    public ResponseEntity<?> bindingException(org.springframework.validation.BindException exception){
        ValidationFailed validationFailed = new ValidationFailed();
        validationFailed.setErrorCode(HttpStatus.BAD_REQUEST.value());
        validationFailed.setMessage("Validation Failed");
        validationFailed.setError(validationUtil.getAllErrors(exception.getFieldErrors()));
        validationFailed.setValidationFailed(Boolean.TRUE);
        return new ResponseEntity<>(validationFailed, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<?> userNameNotFount(UserNameNotFoundException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(Boolean.FALSE);
        apiResponse.setMessage("We have not found your account");
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccountBlockedException.class)
    public ResponseEntity<?> accountBlocked(AccountBlockedException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(Boolean.FALSE);
        apiResponse.setMessage("Due to the maximum failed attempts your account has been blocked for 24 hours");
        return new ResponseEntity<>(apiResponse,HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> exception(HttpMessageNotReadableException exception) {
        ApiError apiError = new ApiError(Boolean.FALSE,HttpStatus.BAD_REQUEST.toString(),"Invalid json request saint");
        Throwable cause = exception.getCause();
        if(cause instanceof JsonMappingException){
            JsonMappingException jpe = (JsonMappingException) cause;
            String msg="Invalid request field : ";
            if (jpe.getPath() != null && jpe.getPath().size() > 0)
                msg = msg+jpe.getPath().get(0).getFieldName();

            ApiError mapping = new ApiError(Boolean.FALSE,HttpStatus.BAD_REQUEST.toString(),msg);
            return new ResponseEntity<>(mapping,HttpStatus.BAD_REQUEST);
        }
        if(cause instanceof JsonParseException){
            ApiError jsonParsngError = new ApiError(Boolean.FALSE,HttpStatus.BAD_REQUEST.toString(),"Invalid json request provided");
            return new ResponseEntity<>(jsonParsngError,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<?> emailAlreadyExist(EmailAlreadyExistException exception){
        ApiError apiError = new ApiError(Boolean.FALSE,HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
        apiError.setValidationFailed(Boolean.TRUE);
        ValidationError validationError = new ValidationError();
        validationError.setMessage(exception.getMessage());
        validationError.setFieldName("email");
        validationError.setRejectedValue(exception.getEmail());
        apiError.setError(Arrays.asList(validationError));
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MobileAlreadyExist.class)
    public ResponseEntity<?> mobileAlreadyExist(MobileAlreadyExist exception){
        ApiError apiError = new ApiError(Boolean.FALSE,HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
        ValidationError validationError = new ValidationError();
        apiError.setValidationFailed(Boolean.TRUE);
        validationError.setMessage(exception.getMessage());
        validationError.setFieldName("mobile");
        validationError.setRejectedValue(exception.getMobile());
        apiError.setError(Arrays.asList(validationError));
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OTPMaxLimitExceedException.class)
    public ResponseEntity<?> otpLimitExceedException(OTPMaxLimitExceedException exception){
        ApiError apiError = new ApiError(Boolean.FALSE,HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
}
