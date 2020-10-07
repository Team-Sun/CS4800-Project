package TeamSun.CS4800Project.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

//TODO look at @Document
public class Person {
	
	@Id //Redundant.
	private ObjectId id; //XXX might be overkill.
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private Date createdDate;
	
	public Person() {
	}
	
	public Person(String firstName) {
		this.firstName = firstName;
		//TODO reimplement
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "{ " + firstName + "," + lastName + "," + userName + "," + password + "}";
	}

}
