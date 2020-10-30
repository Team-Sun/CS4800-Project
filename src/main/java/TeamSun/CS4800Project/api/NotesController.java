package TeamSun.CS4800Project.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import TeamSun.CS4800Project.model.Notes;
import TeamSun.CS4800Project.repositories.NoteRepo;
import TeamSun.CS4800Project.repositories.NoteRepository;

@CrossOrigin(origins = "*", maxAge = 3600) // THIS IS REQUIRED. DO NOT REMOVE
@RestController
@RequestMapping("/api")
public class NotesController {
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	NoteRepo NoteRepo;
	
	@PostMapping("/uploadPage")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	// We'll want a new class here to deal with the request. Will help when forwarding it back to user later.
	public ResponseEntity<Notes> createNote(@RequestBody Notes note) {
		System.out.println("Test");
	  try {
	    Notes _note = noteRepository.save(new Notes(note.getTitle(), note.getDescription(), false));
	    return new ResponseEntity<>(_note, HttpStatus.CREATED);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@GetMapping("/notes")
	@PreAuthorize("permitAll()")
	//@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Notes>> getAllNotes(@RequestParam(required = false) String title) {
	  try {
	    List<Notes> notes = new ArrayList<Notes>();

	    if (title == null)
	      noteRepository.findAll().forEach(notes::add);
	    else
	      noteRepository.findByTitleContaining(title).forEach(notes::add);

	    if (notes.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(notes, HttpStatus.OK);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@GetMapping("/notes/{id}")
	@PreAuthorize("permitAll()")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Notes> getNoteById(@PathVariable("id") String id) {
	  Optional<Notes> noteData = noteRepository.findById(id);

	  if (noteData.isPresent()) {
	    return new ResponseEntity<>(noteData.get(), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}

	@GetMapping("/notes/published")
	@PreAuthorize("permitAll()")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Notes>> findByPublished() {
	  try {
	    List<Notes> notes = noteRepository.findByPublished(true);

	    if (notes.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(notes, HttpStatus.OK);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@PutMapping("/notes/{id}")
	@PreAuthorize("permitAll()")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Notes> updateNote(@PathVariable("id") String id, @RequestBody Notes note) {
	  Optional<Notes> noteData = noteRepository.findById(id);

	  if (noteData.isPresent()) {
	    Notes _note = noteData.get();
	    _note.setTitle(note.getTitle());
	    _note.setDescription(note.getDescription());
	    _note.setPublished(note.isPublished());
	    return new ResponseEntity<>(noteRepository.save(_note), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}
	
	@DeleteMapping("/notes/{id}")
	@PreAuthorize("permitAll()")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteNote(@PathVariable("id") String id) {
	  try {
	    noteRepository.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}

	@DeleteMapping("/notes")
	@PreAuthorize("permitAll()")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteAllNotes() {
	  try {
	    noteRepository.deleteAll();
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	
}