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
import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.response.NoteResponse;
import TeamSun.CS4800Project.response.UserResponse;

/**
 * Takes care of manipulating data and meshing with DAO. >>>>>>> master_temp1
 * 
 * @author Andrew
 *
 */
@Service("noteService")
public class NoteService {

	final int PAGE_SIZE = 10;

	@Autowired
	@Qualifier("mongodb_note")
	NoteDao DB;

	@Autowired
	UserService userService;

	// TODO change to 'save' instead so it's more intuitive.

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
		return DB.delete(note);
	}

	public int size() {
		return DB.size();
	}

	public List<Note> getAll() {
		return DB.getAll();
	}

	public List<Note> getPage(int page) {
		List<Note> ret = new LinkedList<>();
		List<Note> all = DB.getAll();
		for (int i = PAGE_SIZE * (page - 1); i < PAGE_SIZE * page; i++) {
			ret.add(all.get(i));
		}
		return ret;
	}

	public List<Note> findByTitle(String title) {
		return DB.findByTitle(title);
	}

	public List<Note> findByTitleContaining(String title) {
		return DB.findByTitleContaining(title);
	}

	/**
	 * For when specific notes are wanted.
	 * 
	 * @param note
	 * @return
	 */
	public NoteResponse convertToFullResponse(Note note) {
		NoteResponse response = new NoteResponse();
		response.setContent(note.getContent());
		response.setCourse(note.getCourse());
		response.setId(note.getId().toHexString());
		if (note.getOwner() == null) {
			response.setOwner(new UserResponse("Developers"));
		} else {
			if (userService.find(note.getOwner()) == null) {
				//User was deleted
				response.setOwner(new UserResponse("Deleted User"));
			} else {
				User owner = userService.find(note.getOwner());
				response.setOwner(userService.convertToSimpleResponse(owner));
			}
		}
		response.setProfessor(note.getProfessor());
		response.setTitle(note.getTitle());
		response.setRating(note.getRating());
		response.setSemester(note.getSemester());
		response.setDescription(note.getDescription());
		return response;
	}

	/**
	 * For when a lot of notes are wanted at the same time.
	 * 
	 * @param note
	 * @return
	 */
	public NoteResponse convertToSimpleResponse(Note note) {
		NoteResponse response = new NoteResponse();
		response.setCourse(note.getCourse());
		response.setId(note.getId().toHexString());
		response.setProfessor(note.getProfessor());
		response.setTitle(note.getTitle());
		response.setSemester(note.getSemester());
		response.setDescription(note.getDescription());
		if (note.getOwner() == null) {
			response.setOwner(new UserResponse("Developers"));
		} else {
			if (userService.find(note.getOwner()) == null) {
				//User was deleted
				response.setOwner(new UserResponse("Deleted User"));
			} else {
				User owner = userService.find(note.getOwner());
				response.setOwner(userService.convertToSimpleResponse(owner));
			}
		}

		return response;
	}

	// TODO maybe implement differently. Brutal when getting class string over and
	// over.
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
