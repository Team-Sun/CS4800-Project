package TeamSun.CS4800Project.api;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.request.SearchRequest;
import TeamSun.CS4800Project.response.SearchResponse;
import TeamSun.CS4800Project.services.NoteService;
import TeamSun.CS4800Project.services.UserService;

/**
 * For specifically dealing with notes. The controller will specifically handle
 * API REST calls and deal with sending the proper HttpStatus response back. All
 * data manipulation should be (usually) made in small 1-4 line calls to the
 * respective service. This includes searching, adding, updating, deleting,
 * finding users by request, etc... Allows for abstraction and faster coding
 * times.
 * 
 * @author Andrew
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600) // THIS IS REQUIRED. DO NOT REMOVE
@RequestMapping("/api/note")
@RestController
public class NoteController {
	@Autowired
	private NoteService noteService;

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Note> addEntry(@RequestBody Note note, HttpServletRequest request) {
		User clientUser = userService.find(request);
		noteService.save(note);
		clientUser.addNote(note.getId()); // ID is only created after it's inserted. WARN This might result in errors if
														// DB runs concurrently.
		return new ResponseEntity<>(note, HttpStatus.CREATED);

		// TODO May need to catch an exception
	}

	@GetMapping("find/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<Note> getNoteById(@PathVariable("id") ObjectId id) {
		Note note = noteService.findByID(id);

		if (note == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(note, HttpStatus.OK);
		}

	}

	// Testing. TODO remove for deployment.
	@GetMapping("/all")
	@PreAuthorize("permitAll()")
	public ResponseEntity<List<Note>> getAllNotes(@RequestParam(required = false) String title) {
		try {
			List<Note> note = new ArrayList<Note>();

			if (title == null)
				note = noteService.getAll();
			else
				noteService.findByTitle(title).forEach(note::add);

			if (note.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(note, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Note> updateNote(@PathVariable("id") ObjectId id, @RequestBody Note note,
			HttpServletRequest request) {
		User clientUser = userService.find(request);
		Note _note = noteService.findByID(id);

		if ((clientUser.hasRole("ROLE_USER") && clientUser.getId().equals(note.getOwner()))
				|| clientUser.hasRole("ROLE_ADMIN")) {
			if (_note == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				_note.setTitle(note.getTitle());
				_note.setContent(note.getContent());
				_note.setCourse(note.getCourse());
				_note.setProfessor(note.getProfessor());
				// TODO add the file or do it another way that just doesn't update certain
				// parts.
				// TODO LOOKAT possibly making NoteUpdateRequest instead.

				noteService.save(_note);
				return new ResponseEntity<>(_note, HttpStatus.OK); // LOOKAT Might not want to send the note back. Might
																					// just take up unnecessary bandwidth.
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/remove/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Note> removeNote(@PathVariable("id") ObjectId id, HttpServletRequest request) {
		User clientUser = userService.find(request);
		Note note = noteService.findByID(id);

		// Make sure that the current clientUser matches the owner of the note that's
		// attempting to be deleted. Or, make sure the user is admin.
		if ((clientUser.hasRole("ROLE_USER") && clientUser.getId().equals(note.getOwner()))
				|| clientUser.hasRole("ROLE_ADMIN")) {
			if (note != null) {
				noteService.delete(id);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/classes")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<String>> getClasses() {
		List<String> classes = noteService.getClasses();
		if (classes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(classes, HttpStatus.OK);
	}

	@PostMapping("/getNoteByUser")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<String>> getNoteByUser(@RequestBody User user) {
		List<String> notes = new LinkedList<String>();
		for (ObjectId id : userService.find(user.getUsername()).getNotes()) {
			Note temp = noteService.findByID(id);
			if (temp != null) {
				notes.add(temp.getTitle());
			}
		}
		if (notes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(notes, HttpStatus.OK);
	}

	// We use response objects because Spring turns the whole object (including
	// methods) into JSON to be sent.
	@PostMapping("/search")
	@PreAuthorize("permitAll()")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest searchRequest) {
		SearchResponse dummyNote1 = new SearchResponse("testName1", "className1", "testContent1");
		SearchResponse dummyNote2 = new SearchResponse("testName2", "className2", "testContent2");
		List<SearchResponse> dummyList = new LinkedList<>();
		dummyList.add(dummyNote1);
		dummyList.add(dummyNote2);
		return ResponseEntity.ok(dummyList);
	}

}
