package TeamSun.CS4800Project.model;

import org.springframework.data.annotation.Id;

//TODO look at @Document
public class TestModel {
	
	@Id
	long id; //TODO autogenerate incrementing IDs and reconsider userbase size.
	String name;
	
	public TestModel() {
		
	}
	
	public TestModel(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
