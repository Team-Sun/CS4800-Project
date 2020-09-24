package TeamSun.CS4800Project.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import TeamSun.CS4800Project.model.Person;

public interface PersonRepo extends MongoRepository<Person, ObjectId> {
	
}
