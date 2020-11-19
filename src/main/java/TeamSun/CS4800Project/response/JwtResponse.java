package TeamSun.CS4800Project.response;

import java.util.Set;

import org.bson.types.ObjectId;

public class JwtResponse {
	private String accessToken;
	private String type = "Bearer";
	private ObjectId id;
	private String hexId;
	private String username;
	private String email;
	private Set<String> roles;

	public JwtResponse(String accessToken, ObjectId id, String username, String email, Set<String> roles) {
		this.accessToken = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.hexId = id.toHexString();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getHexId() {
		return hexId;
	}

	public void setHexId(String hexId) {
		this.hexId = hexId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRoles() {
		return roles;
	}
}