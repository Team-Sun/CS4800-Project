package TeamSun.CS4800Project.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.request.SignupRequest;
import TeamSun.CS4800Project.response.UserResponse;
import TeamSun.CS4800Project.services.UserService;

/**
 * 
 * For specifically dealing with users and viewing their data.
 *
 * @author Andrew
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600) // THIS IS REQUIRED. DO NOT REMOVE
@RequestMapping("/api/user")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/getAll")
	ResponseEntity<?> getAllUsers() {
		// TODO
		return null;
	}

	/**
	 * Gets user by id. Give only simple response (username, id)
	 *
	 * @param user
	 * @return
	 */
	@GetMapping("/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserResponse> getUserInfo(@PathVariable("id") ObjectId id, HttpServletRequest request) {
		User currentUser = userService.find(id);
		if (currentUser == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.convertToSimpleResponse(currentUser), HttpStatus.OK);
		// return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Gets user by id. Gives full public response (username, id, notes owned)
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@GetMapping("/userProfile/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserResponse> getFullUserInfo(@PathVariable("id") ObjectId id, HttpServletRequest request) {
		User currentUser = userService.find(id);
		if (currentUser == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.convertToResponse(currentUser), HttpStatus.OK);
		// return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/getPrivateInfo")
	@PreAuthorize("hasRole('USER') OR hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<UserResponse> getPrivateUserInfo(@RequestBody User user, HttpServletRequest request) {
		User clientUser = userService.find(request);
		if ((clientUser.hasRole("ROLE_USER") && clientUser.getId().equals(user.getId()) || clientUser.hasRole("ROLE_ADMIN"))) {
			User currentUser = userService.find(user.getId());

			if (currentUser == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<>(userService.convertToPrivateResponse(currentUser), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/update/{id}")
	@PreAuthorize("hasRole('USER') OR hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid SignupRequest user, @PathVariable("id") ObjectId id, HttpServletRequest request) {
		User clientUser = userService.find(request);
		if ((clientUser.hasRole("ROLE_USER") && clientUser.getId().equals(id) || clientUser.hasRole("ROLE_ADMIN"))) {
			User currentUser = userService.find(id);
			
			if (currentUser == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			System.out.println(user.getUsername());
			if (!user.getUsername().isBlank()) {
				currentUser.setUsername(user.getUsername());
			}
			if (!user.getEmail().isBlank()) {
				currentUser.setEmail(user.getEmail());
			}

			if (!user.getPassword().isBlank()) {
				currentUser.setPassword(user.getPassword());
				userService.insert(currentUser);
			} else {
				userService.update(currentUser);
			}
			return new ResponseEntity<>(userService.convertToResponse(currentUser), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<String> removeUser(@PathVariable("id") ObjectId id, HttpServletRequest request) {
		User clientUser = userService.find(request);
		User user = userService.find(id);

		if (clientUser.getId().equals(id) || clientUser.hasRole("ROLE_ADMIN")) {
			userService.delete(user);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
