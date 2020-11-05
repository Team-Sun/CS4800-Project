package TeamSun.CS4800Project.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * 
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
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/find")
	ResponseEntity<?> getUserInfo(@RequestBody User user) {
		// TODO
		return null;
	}

	@DeleteMapping("/remove")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<String> removeUser(@RequestBody User userid, HttpServletRequest request) {
		User clientUser = userService.find(request);
		System.out.println(userid.getId());
		User user = userService.find(userid.getId());
		System.out.println(user);
		User testuser = userService.find("user");
		System.out.println(testuser.getId());

		if (clientUser.hasRole("ROLE_USER") && clientUser.getId().equals(user.getId())
				|| clientUser.hasRole("ROLE_ADMIN")) {
			userService.delete(user);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
