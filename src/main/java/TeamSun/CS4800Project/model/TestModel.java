package TeamSun.CS4800Project.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

//TODO look at @Document
public class TestModel {
	
	@Id //Redundant.
	ObjectId id; //XXX might be overkill.
	String name;
	
	public TestModel() {
		
	}
	
	public TestModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
