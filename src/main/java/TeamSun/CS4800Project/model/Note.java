package TeamSun.CS4800Project.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document(collection = "notes")
public class Note {

	@Id
	private ObjectId id;

	private String name;
	private String message;
	private short rating;
	private String className;
	private MultipartFile file;
	private ObjectId owner; // Must be ObjectId because people can change their username.

	public Note() {

	}

	public Note(String name, String message, String className) {
		this.name = name;
		this.message = message;
		this.className = className;
	}

	public Note(String name, String message, short rating, String className, MultipartFile file) {
		this.name = name;
		this.message = message;
		this.rating = rating;
		this.className = className;
		this.file = file;
	}

	public Note(String name) {
		this.name = name;
	}

	public ObjectId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public short getRating() {
		return rating;
	}

	public void setRating(short rating) {
		this.rating = rating;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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
}
