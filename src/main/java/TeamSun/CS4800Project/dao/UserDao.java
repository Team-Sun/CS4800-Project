package TeamSun.CS4800Project.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import TeamSun.CS4800Project.model.User;

public interface UserDao {
	// TODO javadocs

	int insert(User user);

	Optional<User> findByID(ObjectId id);

	int delete(User user);

	int size();

	/**
	 * Should be used rarely.
	 * 
	 * @return a list of Users
	 */
	List<User> getAll();

	//TODO might not follow dao pattern properly. check out.
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	List<User> findByUsername(String username);

}
