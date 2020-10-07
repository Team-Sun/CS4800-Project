package TeamSun.CS4800Project.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import TeamSun.CS4800Project.model.Person;

public interface PersonDao {
	// TODO javadocs

	int insert(Person person);

	Optional<Person> findByID(ObjectId id);

	int delete(Person person);

	int size();

	/**
	 * Should be used rarely.
	 * 
	 * @return a list of Persons
	 */
	List<Person> getAll();
	
	List<Person> findByFirstName(String name);

}
