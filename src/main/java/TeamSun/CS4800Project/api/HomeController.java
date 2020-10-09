package TeamSun.CS4800Project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.services.UserService;

/**
 * For specifically viewing basic home elements. This includes: About, FAQ, TOS,
 * etc...
 * 
 * @author Andrew
 *
 */
@RequestMapping
@RestController
public class HomeController {

	@Autowired
	private UserService userService;

	@PostMapping("/api/test/add")
	public String addEntry(@ModelAttribute("name") String name) {
		userService.insert(new User(name));
		return "inserting " + name;
	}

	@GetMapping("/api/test/getall")
	public String getAllEntries() {
		return userService.toString();
	}
}
