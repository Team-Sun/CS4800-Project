package TeamSun.CS4800Project.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Note {
	
	@Id
	private ObjectId id;
	private String name;
	private String message;
	private short rating;

	public Note() {
		
	}

}
