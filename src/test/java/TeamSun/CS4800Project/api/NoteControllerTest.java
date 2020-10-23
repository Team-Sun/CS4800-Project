package TeamSun.CS4800Project.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.services.NoteService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteControllerTest {

    @Autowired
    TestRestTemplate template;

    @Autowired
    NoteService noteService;

    @Test
    void testAddEntry() throws Exception{
        template.postForObject("/note/add", new Note("TestNote"), String.class);
        Assertions.assertTrue(noteService.findByTitle("TestNote").size() == 1 , "Note inserted");
        noteService.delete(noteService.findByTitle("TestNote").get(0));
        Assertions.assertTrue(noteService.findByTitle("TestNote").size() == 0 , "Note deleted");
    }
}