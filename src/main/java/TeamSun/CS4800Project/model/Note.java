package TeamSun.CS4800Project.model;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Note {

	@Id
	private ObjectId id;

	private String title;
	private String content;
	private short rating;
	private String course;
	private Binary file;
	private String fileType;
	private ObjectId owner; // Must be ObjectId because people can change their username.
	private String professor;

	public Note() {

	}

	public Note(String name, String message, String className) {
		this.title = name;
		this.content = message;
		this.course = className;
	}

	public Note(String name, String message, short rating, String course, Binary file) {
		this.title = name;
		this.content = message;
		this.rating = rating;
		this.course = course;
		this.file = file;
	}

	public Note(String name) {
		this.title = name;
	}

	public ObjectId getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String message) {
		this.content = message;
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

	public Binary getFile() {
		return file;
	}

	public void setFile(Binary file) {
		this.file = file;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
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
