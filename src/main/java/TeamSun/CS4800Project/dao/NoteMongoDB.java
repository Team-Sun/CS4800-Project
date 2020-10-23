package TeamSun.CS4800Project.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.repositories.NoteRepo;

@Repository("mongodb_note")
public class NoteMongoDB implements NoteDao {
	
	@Autowired
	NoteRepo repo;

	@Override
	public int save(Note note) {
		//TODO handle exceptions.
		repo.save(note);
		return 1; //TEMP
	}

	@Override
	public Optional<Note> findByID(ObjectId id) {
		return repo.findById(id);
	}

	@Override
	public int delete(Note note) {
		//TODO handle exceptions.
		repo.delete(note);
		return 1; //TEMP
	}

	@Override
	public int size() {
		return (int) repo.count();
	}

	@Override
	public List<Note> getAll() {
		return repo.findAll();
	}

	@Override
	public List<Note> findByTitle(String title) {
		return repo.findByTitle(title);
	}
}
