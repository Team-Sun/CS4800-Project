package TeamSun.CS4800Project.response;

import java.util.HashSet;
import java.util.Set;

public class UserResponse {

	private String id;
	private String username;
	private Set<String> notes = new HashSet<>();
	private String email;

	public UserResponse(String username, Set<String> notes, String email, String id) {
		this.username = username;
		this.notes = notes;
		this.email = email;
		this.id = id;
	}
	
	public UserResponse(String username) {
		this.username = username;
	}

	public UserResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getNotes() {
		return notes;
	}

	public void setNotes(Set<String> notes) {
		this.notes = notes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
