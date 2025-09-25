package Data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginModel {

	
	public String username;
	public String password;
	
	@JsonProperty("username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonProperty("password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
