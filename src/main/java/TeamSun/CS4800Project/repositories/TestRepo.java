package TeamSun.CS4800Project.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import TeamSun.CS4800Project.model.TestModel;

public interface TestRepo extends MongoRepository<TestModel, ObjectId> {

	public Optional<TestModel> findById(ObjectId id);
	
}
