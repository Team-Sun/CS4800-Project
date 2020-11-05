package TeamSun.CS4800Project.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import TeamSun.CS4800Project.dao.NoteDao;
import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.response.NoteResponse;

/**
 * Takes care of manipulating data and meshing with DAO.
 * @author Andrew
 *
 */
@Service("noteService")
public class NoteService {

	@Autowired
	@Qualifier("mongodb_note")
	NoteDao DB;
	
	//TODO change to 'save' instead so it's more intuitive.
	public int save(Note note) {
		return DB.save(note);
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
	
	public List<Note> getAll() {
		return DB.getAll();
	}

	public List<Note> findByTitle(String title) {
		return DB.findByTitle(title);
	}
	
	public List<Note> findByTitleContaining(String title){
		return DB.findByTitleContaining(title);
	}
	
	public NoteResponse convertToResponse(Note note) {
		NoteResponse response = new NoteResponse();
		response.setContent(note.getContent());
		response.setCourse(note.getCourse());
		response.setFile(note.getFile());
		response.setId(note.getId().toHexString());
		response.setOwner(note.getOwner());
		response.setProfessor(note.getProfessor());
		response.setTitle(note.getTitle());
		response.setRating(note.getRating());
		return response;
	}
	
	//TODO maybe implement differently. Brutal when getting class string over and over.
	public List<String> getClasses() {
		List<String> output = new LinkedList<String>(); // TODO CHANGE TO SET
		for (Note note : DB.getAll()) {
			output.add(note.getCourse());
		}
		return output;
	}
	
	public void delete(ObjectId id) throws IllegalArgumentException {
		Note temp = findByID(id);
		if (temp != null) {
			DB.delete(temp);
		} else {
			throw new IllegalArgumentException("No note with given ObjectID");
		}
	}
	
}
