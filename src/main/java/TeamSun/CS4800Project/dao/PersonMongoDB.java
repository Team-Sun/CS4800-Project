package TeamSun.CS4800Project.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import TeamSun.CS4800Project.model.Person;
import TeamSun.CS4800Project.repositories.PersonRepo;

@Repository("mongodb_person")
public class PersonMongoDB implements PersonDao {

	@Autowired
	private PersonRepo repo;

	@Override
	public int insert(Person person) {
		// TODO handle error.
		repo.save(person);
		return 1; //TEMP
	}

	@Override
	public Optional<Person> findByID(ObjectId id) {
		return repo.findById(id);
	}

	@Override
	public int delete(Person person) {
		//TODO handle exceptions.
		repo.delete(person);
		return 1; //TEMP
	}

	@Override
	public int size() {
		return (int) repo.count();
	}

	@Override
	public List<Person> getAll() {
		return repo.findAll();
	}

}
