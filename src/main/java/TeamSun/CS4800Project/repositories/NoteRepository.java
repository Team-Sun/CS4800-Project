package TeamSun.CS4800Project.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import TeamSun.CS4800Project.model.Notes;

public interface NoteRepository extends MongoRepository<Notes, String> {
	 List<Notes> findByTitleContaining(String title);
	 List<Notes> findByPublished(boolean published);
}