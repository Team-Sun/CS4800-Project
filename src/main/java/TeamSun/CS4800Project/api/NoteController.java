package TeamSun.CS4800Project.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.response.NoteResponse;
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
@RequestMapping("/api")
@RestController
public class NoteController {
	@Autowired
	private NoteService noteService;

	@Autowired
	private UserService userService;

	@PostMapping("/note")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<NoteResponse> addEntry(@RequestPart Note note, @RequestPart(required = false) MultipartFile file, HttpServletRequest request) {
		if (file != null) {
			try {
				note.setFileType(file.getContentType()); // TODO validate PDF in frontend and backend here.
				note.setFile(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println(note.getContent());
		}

		User clientUser = userService.find(request);
		note.setOwner(clientUser.getId());

		noteService.save(note);
		// ID is only created after it's inserted. WARN This might result in errors if
		// DB runs concurrently.
		clientUser.addNote(note.getId());
		return new ResponseEntity<>(noteService.convertToFullResponse(note), HttpStatus.CREATED);

		// TODO May need to catch an exception
	}

	// Thanks to
	// https://stackoverflow.com/questions/59686660/how-to-send-generated-pdf-document-to-frontend-in-restfull-way-in-spring-boot
	@GetMapping("/file/{id:.+}")
	// @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@PreAuthorize("permitAll()")
	public void getFile(@PathVariable("id") ObjectId id, HttpServletRequest request, HttpServletResponse response) {
		// User clientUser = userService.find(request);
		Note note = noteService.findByID(id);
		try {
			response.setHeader("Content-Disposition", String.format("inline; filename=\"testnote.pdf" + "\""));
			response.getOutputStream().write(note.getFile().getData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@GetMapping("/note/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<NoteResponse> getNoteById(@PathVariable("id") ObjectId id) {
		Note note = noteService.findByID(id);

		if (note == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(noteService.convertToFullResponse(note), HttpStatus.OK);
		}

	}

	/**
	 * Gets all notes or gets note by given title if provided.
	 * 
	 * @param title
	 * @return
	 */
	@GetMapping("/note")
	@PreAuthorize("permitAll()")
	public ResponseEntity<List<NoteResponse>> getAllNotes(@RequestParam(required = false) String title, @RequestBody(required = false) Integer page) {
		try {
			List<NoteResponse> noteList = new ArrayList<NoteResponse>();

			if (title == null) {
				if (page == null) {
					for (Note note : noteService.getAll()) {
						noteList.add(noteService.convertToSimpleResponse(note));
					}
				} else {
					for (Note note : noteService.getPage(page)) {
						noteList.add(noteService.convertToSimpleResponse(note));
					}
				}
			} else {
				for (Note note : noteService.findByTitleContaining(title)) {
					noteList.add(noteService.convertToSimpleResponse(note));
				}

				if (noteList.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			return new ResponseEntity<>(noteList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/note/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<NoteResponse> updateNote(@PathVariable("id") ObjectId id, @RequestBody Note note, HttpServletRequest request) {
		User clientUser = userService.find(request);
		Note noteToEdit = noteService.findByID(id);

		// If user and that user matches the note's owner or if user is admin then allow
		// change.
		if ((clientUser.hasRole("ROLE_USER") && clientUser.getId().equals(note.getOwner())) || clientUser.hasRole("ROLE_ADMIN")) {
			if (noteToEdit == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				noteToEdit.setTitle(note.getTitle());
				noteToEdit.setContent(note.getContent());
				noteToEdit.setCourse(note.getCourse());
				noteToEdit.setProfessor(note.getProfessor());
				noteToEdit.setSemester(note.getSemester());
				noteToEdit.setDescription(note.getDescription());
				// TODO add the file or do it another way that just doesn't update certain
				// parts.
				// TODO LOOKAT possibly making NoteUpdateRequest instead.

				noteService.save(noteToEdit);
				// LOOKAT Might not want to send the note back. Might just take up unnecessary
				// bandwidth.
				return new ResponseEntity<>(noteService.convertToFullResponse(noteToEdit), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/note/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Note> removeNote(@PathVariable("id") ObjectId id, HttpServletRequest request) {
		User clientUser = userService.find(request);
		Note note = noteService.findByID(id);

		// Make sure that the current clientUser matches the owner of the note that's
		// attempting to be deleted. Or, make sure the user is admin.
		if ((clientUser.hasRole("ROLE_USER") && clientUser.getId().equals(note.getOwner())) || clientUser.hasRole("ROLE_ADMIN")) {
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

	@PostMapping("/getNotesByUser/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<NoteResponse>> getNoteByUser(@PathVariable("id") ObjectId id) {
		List<NoteResponse> notes = new LinkedList<>();
		User user = userService.find(id);
		
		if (user != null) {
			notes = noteService.getOwnedNotes(user);
			if (notes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(notes, HttpStatus.OK);
	}
}
