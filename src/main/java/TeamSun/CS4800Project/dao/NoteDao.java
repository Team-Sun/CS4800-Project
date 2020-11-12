package TeamSun.CS4800Project.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import TeamSun.CS4800Project.model.Note;

/**
 * Any DB we want to use must implement this interface. This allows abstraction
 * and separation of actual DB implementation and API/controller/service code.
 * 
 * @author Andrew
 *
 */
public interface NoteDao {
	// TODO javadocs

	int save(Note note);

	Optional<Note> findByID(ObjectId id);

	int delete(Note note);

	int size();

	/**
	 * Should be used rarely.
	 * 
	 * @return a list of Notes
	 */
	List<Note> getAll();

	List<Note> findByTitle(String title);
	
	List<Note> findByTitleContaining(String title);

}
