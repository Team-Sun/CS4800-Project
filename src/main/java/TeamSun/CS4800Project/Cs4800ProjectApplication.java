package TeamSun.CS4800Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import TeamSun.CS4800Project.services.NoteService;
import TeamSun.CS4800Project.services.UserService;

@SpringBootApplication
public class Cs4800ProjectApplication {

	@Autowired
	UserService userService;

	@Autowired
	NoteService noteService;

	public static void main(String[] args) {
		SpringApplication.run(Cs4800ProjectApplication.class, args);
	}
}
