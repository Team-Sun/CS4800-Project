package TeamSun.CS4800Project.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import TeamSun.CS4800Project.dao.UserMongoDB;
import TeamSun.CS4800Project.model.User;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	@Qualifier("mongodb_user")
	UserMongoDB DB;
	
	public int insert(User user) {
		return DB.insert(user);
	}

	public User findByID(ObjectId id) {
		Optional<User> temp = DB.findByID(id);
		if (temp.isEmpty()) {
			return null;
		}
		return temp.get();
	}
	
	public int delete(User user) {
		return	 DB.delete(user);
	}
	
	public int size() {
		return DB.size();
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

	public List<User> findByUsername(String username) {
		//TODO convert optional
		return DB.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//TODO deal with. We can't allow duplicate usernames or whatnot.
		List<User> users = DB.findByUsername(username);
		return users.get(0);
	}

}
