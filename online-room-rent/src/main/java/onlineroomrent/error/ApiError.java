package onlineroomrent.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ApiError {

	private Boolean status;
	private String message;
	private String errorCode;

	public ApiError(Boolean status,String errorCode, String message) {
		this.status = status;
		this.message = message;
		this.errorCode=errorCode;
	}
}