package TeamSun.CS4800Project.request;

public class SearchRequest {

	private String noteName;
	private String professor;
	private String className;

	public String getNote() {
		return noteName;
	}

	public void setNote(String note) {
		this.noteName = note;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
