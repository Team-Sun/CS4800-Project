package TeamSun.CS4800Project.response;

import org.bson.types.ObjectId;

public class SearchResponse {

	private ObjectId id;
	private String title;
	private String course;
	private String content;
	
	public SearchResponse(String title, String course, String content, ObjectId id) {
		this.title = title;
		this.course = course;
		this.setContent(content);
		this.id = id;
	}

	public SearchResponse(String title, String course, String content) {
		this.title = title;
		this.course = course;
		this.setContent(content);
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}
