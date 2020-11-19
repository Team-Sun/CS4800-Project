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
		for (Note note : noteService.findByTitle("testNoteName")) {
			noteService.delete(note);
		}
		Note note = new Note("testNoteName", "testNoteMessage", "testNoteClassName");
		noteService.save(note);

		for (Note n : noteService.findByTitle("Unit Testing")) {
			noteService.delete(n);
		}
		note = new Note("Unit Testing",
				"A unit is the smallest testable part of any software. It usually has one or a few inputs and usually a single output. In procedural programming, a unit may be an individual program, function, procedure, etc. In object-oriented programming, the smallest unit is a method, which may belong to a base / super class, abstract class or derived / child class. (Some treat a module of an application as a unit. This is to be discouraged as there will probably be many individual units within that module.) Unit testing frameworks, drivers, stubs, and mock / fake objects are used to assist in unit testing.",
				"CS 4800", "Adam Smith", "Spring 2019", "A Unit Testing Note");
		noteService.save(note);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void makeAdmin() {
		if (userService.find("admin") != null) {
			userService.delete(userService.find("admin"));
		}
		User user = new User("admin", "admin@admin.com", "admin");
		user.addRole("ROLE_ADMIN");
		userService.insert(user);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void makeUser() {
		if (userService.find("user") != null) {
			userService.delete(userService.find("user"));
		}
		User user = new User("user", "user@user.com", "user");
		user.addRole("ROLE_USER");
		userService.insert(user);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void makeMod() {
		if (userService.find("mod") != null) {
			userService.delete(userService.find("mod"));
		}

		User user = new User("mod", "mod@mod.com", "mod");
		user.addRole("ROLE_MODERATOR");
		userService.insert(user);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void makeTest() {
		if (userService.find("test") != null) {
			userService.delete(userService.find("test"));
		}
		User user = new User("test", "test@test.com", "test");
		user.addRole("ROLE_TEST");
		userService.insert(user);
	}

}
