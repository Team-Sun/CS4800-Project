package TeamSun.CS4800Project.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.model.Notes;

import java.util.List;

public interface NoteRepo extends MongoRepository<Note, ObjectId> {

	List<Note> findByTitle(String name);
	List<Note> findByTitleContaining(String title);

	//List<Note> findByName(String name);
	//List<Note> findByPublished(boolean published);
}
