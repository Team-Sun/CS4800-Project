package TeamSun.CS4800Project.request;

import javax.validation.constraints.NotBlank;

/**
 * For specifically having frontend send this data and spring boot linking it
 * into the class properly. Variable names must match the json string sent,
 * otherwise spring won't make the class completely or properly.
 * 
 * @author Andrew
 *
 */
public class LoginRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}