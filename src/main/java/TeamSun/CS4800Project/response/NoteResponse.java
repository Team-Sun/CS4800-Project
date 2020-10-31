package TeamSun.CS4800Project.response;

import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

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
	private MultipartFile file;
	private ObjectId owner; // Must be ObjectId because people can change their username.
	private String professor;

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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public ObjectId getOwner() {
		return owner;
	}

	public void setOwner(ObjectId owner) {
		this.owner = owner;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

}
