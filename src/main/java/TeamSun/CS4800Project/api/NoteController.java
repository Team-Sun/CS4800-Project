package TeamSun.CS4800Project.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.services.NoteService;
import TeamSun.CS4800Project.services.UserService;

/**
 * For specifically dealing with notes.
 * 
 * @author Andrew
 *
 */
@RequestMapping("/api/note")
@RestController
public class NoteController {
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/add")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Note addEntry(@RequestBody Note note, HttpServletRequest request) {
		User user = userService.find(request);
		noteService.insert(note);
		user.addNote(note.getId()); // ID is only created after it's inserted. WARN This might result in errors if DB runs concurrently.
		return note;
	}

	@DeleteMapping("/remove")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeNote(ObjectId id) {
		//TODO verify note is owned by user.
		noteService.remove(id);
	}

	@GetMapping("/classes")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<String> getClasses() {
		return noteService.getClasses();
	}

}
