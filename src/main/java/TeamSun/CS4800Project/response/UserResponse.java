package TeamSun.CS4800Project.response;

import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;

public class UserResponse {

	private String username;
	private Set<ObjectId> notes = new HashSet<>();
	private String email;

	public UserResponse(String username, Set<ObjectId> notes, String email) {
		this.username = username;
		this.notes = notes;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<ObjectId> getNotes() {
		return notes;
	}

	public void setNotes(Set<ObjectId> notes) {
		this.notes = notes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
