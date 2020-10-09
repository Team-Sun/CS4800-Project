package TeamSun.CS4800Project.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import TeamSun.CS4800Project.model.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
	
	List<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
