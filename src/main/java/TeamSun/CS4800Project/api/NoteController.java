package TeamSun.CS4800Project.api;

import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
