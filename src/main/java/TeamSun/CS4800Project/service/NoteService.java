package TeamSun.CS4800Project.service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import TeamSun.CS4800Project.dao.NoteMongoDB;
import TeamSun.CS4800Project.model.Note;

@Service
public class NoteService {

	@Autowired
	@Qualifier("mongodb_note")
	NoteMongoDB DB;
	
	public int insert(Note note) {
		return DB.insert(note);
	}

	public Note findByID(ObjectId id) {
		Optional<Note> temp = DB.findByID(id);
		if (temp.isEmpty()) {
			return null;
		}
		return temp.get();
	}
	
	public int delete(Note note) {
		return	 DB.delete(note);
	}
	
	public int size() {
		return DB.size();
	}
	
}
