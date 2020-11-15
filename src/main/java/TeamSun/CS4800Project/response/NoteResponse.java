package TeamSun.CS4800Project.response;

import org.bson.types.ObjectId;

/**
 * This is for specifically sending data back to the frontend from the backend.
 * Allows methods currently in Note class to not be sent, saving resources and
 * bandwidth.
 * 
 * @author Andrew
 *
 */
public class NoteResponse {

	private String id;
	private String title;
	private String content;
	private short rating;
	private String course;
	private String semester;
	private UserResponse owner; // owner == username
	private String professor;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public short getRating() {
		return rating;
	}

	public void setRating(short rating) {
		this.rating = rating;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public UserResponse getOwner() {
		return owner;
	}

	public void setOwner(UserResponse owner) {
		this.owner = owner;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
