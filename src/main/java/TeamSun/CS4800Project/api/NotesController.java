//package TeamSun.CS4800Project.api;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import TeamSun.CS4800Project.model.Note;
//import TeamSun.CS4800Project.repositories.NoteRepo;
//
//@CrossOrigin(origins = "http://localhost:8081")
//@RestController
//@RequestMapping("/api")
//public class NotesController {
//	
//	@Autowired
//	NoteRepo noteRepo;
//	
//	@PostMapping("/search")
//	public ResponseEntity<Note> createNote(@RequestBody Note note) {
//		try {
//			Note _note = noteRepo.save(new Note(note.getName(), note.getMessage(), note.getClassName(), false));
//			return new ResponseEntity<>(_note, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	@GetMapping("/search")
//	public ResponseEntity<List<Note>> getAllNotes(@RequestParam(required = false) String name) {
//		try {
//			List<Note> notes = new ArrayList<Note>();
//			
//			if (name == null)
//				noteRepo.findAll().forEach(notes::add);
//			else
//				noteRepo.findByName(name).forEach(notes::add);
//			
//			if (notes.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			
//			return new ResponseEntity<>(notes, HttpStatus.OK);
//			
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//	}
//	
//	
//}