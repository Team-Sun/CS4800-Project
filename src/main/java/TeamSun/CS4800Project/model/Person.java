package TeamSun.CS4800Project.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

//TODO look at @Document
public class Person {
	
	@Id //Redundant.
	private ObjectId id; //XXX might be overkill.
	private String name;
	private String userName;
	
	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
