package Auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class AuthResponse {
	@JsonProperty("error")
	private String error = null;
	
	public AuthResponse() {
		
	}
	
	public String getError() {
		return error;
	}

	public void setError(String badRequest) {
		this.error = badRequest;
	}
}
