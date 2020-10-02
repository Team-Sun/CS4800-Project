package TeamSun.CS4800Project.service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import TeamSun.CS4800Project.dao.PersonMongoDB;
import TeamSun.CS4800Project.model.Person;

@Service
public class PersonService {
	
	@Autowired
	@Qualifier("mongodb_person")
	PersonMongoDB DB;
	
	public int insert(Person person) {
		return DB.insert(person);
	}

	public Person findByID(ObjectId id) {
		Optional<Person> temp = DB.findByID(id);
		if (temp.isEmpty()) {
			return null;
		}
		return temp.get();
	}
	
	public int delete(Person person) {
		return	 DB.delete(person);
	}
	
	public int size() {
		return DB.size();
	}
	
	@Override
	public String toString() {
		return DB.getAll().toString();
	}

}
