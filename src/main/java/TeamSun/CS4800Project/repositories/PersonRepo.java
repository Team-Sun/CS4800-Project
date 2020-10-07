package TeamSun.CS4800Project.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import TeamSun.CS4800Project.model.Person;

/*
 *  This is where we add stuff like findByFirstName()
 *  Reference https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
 *  We can add searches by specific variables in the Model.
 */

public interface PersonRepo extends MongoRepository<Person, ObjectId> {
	
	List<Person> findByFirstName(String firstName);
	
}
