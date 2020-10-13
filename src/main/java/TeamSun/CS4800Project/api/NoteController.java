package TeamSun.CS4800Project.api;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.services.NoteService;

/**
 * For specifically viewing a single upload.
 * 
 * @author Andrew
 *
 */
@RequestMapping("/note")
@RestController
public class NoteController {
	@Autowired
	private NoteService noteService;

	@PostMapping("/add")
	public Note addEntry(@RequestBody Note note) {
		noteService.insert(note);
		return note;
	}

	@DeleteMapping("/remove")
   public void removeNote(ObjectId id) {
		noteService.remove(id);
   }

}
