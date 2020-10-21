package TeamSun.CS4800Project.api;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.request.SearchRequest;
import TeamSun.CS4800Project.services.NoteService;

/**
 * For specifically searching through the DB of notes.
 * 
 * @author Andrew
 *
 */
@RequestMapping("/api/search")
@RestController
public class SearchController {
	
	@Autowired
	NoteService noteService;
	
	@PostMapping
	public List<Note> search(@RequestBody SearchRequest searchRequest) {
		Note dummyNote1 = new Note("testName1", "testMessage1", (short) 2, "className1", null);
		Note dummyNote2 = new Note("testName2", "testMessage2", (short) 3, "className2", null);
		List<Note> dummyList = new LinkedList<>();
		dummyList.add(dummyNote1);
		dummyList.add(dummyNote2);
		return dummyList;
	}

}
