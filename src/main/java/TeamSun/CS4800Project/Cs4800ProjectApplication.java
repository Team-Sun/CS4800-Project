package TeamSun.CS4800Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import TeamSun.CS4800Project.model.Note;
import TeamSun.CS4800Project.model.User;
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

	// TODO WARN Remove for deployment!!!!
	// https://stackoverflow.com/questions/27405713/running-code-after-spring-boot-starts

	@EventListener(ApplicationReadyEvent.class)
	public void makeNote() {
		for (Note note : noteService.findByName("testNoteName")) {
			noteService.delete(note);
		}
		Note note = new Note("testNoteName", "testNoteMessage", "testNoteClassName");
		noteService.insert(note);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void makeAdmin() {
		userService.delete(userService.find("admin"));
		User user = new User("admin", "admin@admin.com", "admin");
		user.addRole("ROLE_ADMIN");
		userService.insert(user);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void makeUser() {
		userService.delete(userService.find("user"));
		User user = new User("user", "user@user.com", "user");
		user.addRole("ROLE_USER");
		userService.insert(user);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void makeMod() {
		userService.delete(userService.find("mod"));
		User user = new User("mod", "mod@mod.com", "mod");
		user.addRole("ROLE_MOD");
		userService.insert(user);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void makeTest() {
		userService.delete(userService.find("test"));
		User user = new User("test", "test@test.com", "test");
		user.addRole("ROLE_TEST");
		userService.insert(user);
	}

}
