package onlineroomrent.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import onlineroomrent.validation.ValidationError;

import java.util.Date;
import java.util.List;

@Data
public class ApiError {

	private Boolean status;
	private String message;
	private String errorCode;
	private Boolean validationFailed;
	private List<ValidationError> error;
	public ApiError(Boolean status,String errorCode, String message) {
		this.status = status;
		this.message = message;
		this.errorCode=errorCode;
	}
}