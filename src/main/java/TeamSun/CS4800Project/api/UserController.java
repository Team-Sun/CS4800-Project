package TeamSun.CS4800Project.api;

import TeamSun.CS4800Project.response.UserResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.services.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * For specifically dealing with users and viewing their data.
 * @author Andrew
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600) // THIS IS REQUIRED. DO NOT REMOVE
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

	@PutMapping("/update")
	@PreAuthorize("hasRole('USER') OR hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<UserResponse> updateUser(@RequestBody User user, HttpServletRequest request) {
		User clientUser = userService.find(request);
		if((clientUser.hasRole("ROLE_USER") && clientUser.getId().equals(user.getId()) || clientUser.hasRole("ROLE_ADMIN"))) {
			User currentUser = userService.find(user.getId());

			if(currentUser == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			if (user.getUsername() != null) {
				currentUser.setUsername(user.getUsername());
			}
			if (user.getEmail() != null) {
				currentUser.setEmail(user.getEmail());
			}

			if (user.getPassword() != null) {
				currentUser.setPassword(user.getPassword());
				userService.insert(currentUser);
			} else {
				userService.update(currentUser);
			}

		} else {
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

		return new ResponseEntity<>(userService.convertToResponse(user), HttpStatus.OK);
	}

	@DeleteMapping("/remove")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<String> removeUser(@RequestBody User userId, HttpServletRequest request) {
		User clientUser = userService.find(request);
		User user = userService.find(userId.getId());

		if(clientUser.hasRole("ROLE_USER") && clientUser.getId().equals(user.getId()) || clientUser.hasRole("ROLE_ADMIN")) {
			userService.delete(user);
		}
		else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
