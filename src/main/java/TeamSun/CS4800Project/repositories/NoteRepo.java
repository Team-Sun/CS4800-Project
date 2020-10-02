package TeamSun.CS4800Project.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import TeamSun.CS4800Project.model.Note;

public interface NoteRepo extends MongoRepository<Note, ObjectId> {
	
}
