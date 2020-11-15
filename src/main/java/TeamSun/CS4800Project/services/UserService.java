package TeamSun.CS4800Project.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import TeamSun.CS4800Project.dao.UserDao;
import TeamSun.CS4800Project.jwt.JwtUtils;
import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.request.SignupRequest;
import TeamSun.CS4800Project.response.UserResponse;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("mongodb_user")
	UserDao DB;
	
	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	PasswordEncoder encoder;

	public int insert(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return DB.save(user);
	}
	
	public int update(User user) { //TODO determine if needed (insert might work better)
		return DB.save(user);
	}

	public int insert(SignupRequest request) {
		User user = new User(request.getUsername(), request.getEmail(), encoder.encode(request.getPassword()));	
		user.addRole("ROLE_USER");
		
		return DB.save(user);
	}

	public User find(ObjectId id) {
		Optional<User> temp = DB.findByID(id);
		if (temp.isEmpty()) {
			return null;
		}
		return temp.get();
	}
	
	public User find(String username) {
		return DB.findByUsername(username);
	}
	
	public User find(HttpServletRequest request) {
		String token = jwtUtils.parseJwt(request);
		if (jwtUtils.validateJwtToken(token)) {
			return DB.findByUsername(jwtUtils.getUserNameFromJwtToken(token));
		}
		return null;
	}
	
	public void setAuth(String username, boolean authenticated) {
		User temp = find(username);
		temp.setAuthenticated(authenticated);
		update(temp);
	}

	public int delete(User user) {
		return DB.delete(user);
	}

	public int size() {
		return DB.size();
	}

	public UserResponse convertToResponse(User user) {
		UserResponse response = new UserResponse();
		response.setId(user.getId().toHexString());
		response.setUsername(user.getUsername());
		Set<String> notes = new HashSet<>();
		for (ObjectId noteid : user.getNotes()) {
			notes.add(noteid.toHexString());
		}
		response.setNotes(notes);
		return response;
	}
	
	public UserResponse convertToPrivateResponse(User user) {
		UserResponse response = convertToResponse(user);
		response.setEmail(user.getEmail());
		return response;
	}
	
	public UserResponse convertToSimpleResponse(User user) {
		UserResponse response = new UserResponse();
		System.out.println(user.getUsername());
		response.setId(user.getId().toHexString());
		response.setUsername(user.getUsername());
		return response;
	}

	@Override
	public String toString() {
		return DB.getAll().toString();
	}

	public Boolean existsByUsername(String username) {
		return DB.existsByUsername(username);
	}

	public Boolean existsByEmail(String email) {
		return DB.existsByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = DB.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Couldn't find username: " + username + " in database.");
		}
		return user;
	}

}
