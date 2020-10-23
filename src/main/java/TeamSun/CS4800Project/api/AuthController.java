package TeamSun.CS4800Project.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.jwt.JwtUtils;
import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.request.LoginRequest;
import TeamSun.CS4800Project.request.SignupRequest;
import TeamSun.CS4800Project.response.JwtResponse;
import TeamSun.CS4800Project.response.MessageResponse;
import TeamSun.CS4800Project.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600) // THIS IS REQUIRED. DO NOT REMOVE
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// This will catch incorrect credentials without going into the AuthTokenFilter.
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		// This will allow authTokenFilter to complete.
		userService.setAuth(loginRequest.getUsername(), true);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		User newUser = (User) authentication.getPrincipal();

		return ResponseEntity.ok(
				new JwtResponse(jwt, newUser.getId(), newUser.getUsername(), newUser.getEmail(), newUser.getStringRoles()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		// Check is username exists
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		// Check is email already exists
		if (userService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		userService.insert(signUpRequest);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logoutUser(HttpServletRequest request) {
		User clientUser = userService.find(request);
		userService.setAuth(clientUser.getUsername(), false);
		return new ResponseEntity<>("User logged out successfully!", HttpStatus.OK);
	}

}