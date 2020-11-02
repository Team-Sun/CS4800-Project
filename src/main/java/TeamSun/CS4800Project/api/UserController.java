package TeamSun.CS4800Project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.services.UserService;

/**
 * 
 * For specifically dealing with users and viewing their data.
 * @author Andrew
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getAll")
	ResponseEntity<?> getAllUsers() {
		// TODO
		return null;
	}
	
	/**
	 * Gets user by username.
	 * @param user
	 * @return
	 */
	@PostMapping("/find")
	ResponseEntity<?> getUserInfo(@RequestBody User user) {
		// TODO
		return null;
	}
}
