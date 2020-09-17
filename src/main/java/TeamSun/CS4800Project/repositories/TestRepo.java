package TeamSun.CS4800Project.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import TeamSun.CS4800Project.model.TestModel;

public interface TestRepo extends MongoRepository<TestModel, Long> {

	public Optional<TestModel> findById(long id);
	
}
