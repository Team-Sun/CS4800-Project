package TeamSun.CS4800Project.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import TeamSun.CS4800Project.model.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
	
	User findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
