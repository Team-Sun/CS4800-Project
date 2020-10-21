package TeamSun.CS4800Project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.services.UserService;

@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
@RestController
public class AdminController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public String addEntry(@ModelAttribute("name") String name) {
		userService.insert(new User(name));
		return "inserting " + name;
	}
	
	@GetMapping("/find")
	public User find(@ModelAttribute("name") String name) {
		return userService.find(name);
	}

	@GetMapping("/getall")
	public String getAllEntries() {
		return userService.toString();
	}
	
	/* 
	 * reference: https://stackoverflow.com/questions/21824012/spring-modelattribute-vs-requestbody for @RequestBody
	 * Essentially, @RequestBody parses sent JSON and turns it into applicable given class (in this case, 'Person').
	 * 
	 * Example JSON:
	 * 
	 * {
	 * "firstName":"UniqueFirstName",
	 * "lastName":"UniqueLastName",
	 * "userName":"UniqueUserName",
	 * "password":"SomePassword"
	 * }
	 * 
	 */
	@PostMapping("/insert")
	public User insertEntry(@RequestBody User person) {
		userService.insert(person);
		return person;
	}
	
}
