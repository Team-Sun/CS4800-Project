package TeamSun.CS4800Project.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import TeamSun.CS4800Project.model.User;
import TeamSun.CS4800Project.repositories.UserRepository;

@Repository("mongodb_user")
public class UserMongoDB implements UserDao {

	@Autowired
	private UserRepository repo;

	@Override
	public int save(User user) {
		// TODO handle error.
		repo.save(user);
		return 1; //TEMP
	}

	@Override
	public Optional<User> findByID(ObjectId id) {
		return repo.findById(id);
	}

	@Override
	public int delete(User user) {
		//TODO handle exceptions.
		repo.delete(user);
		return 1; //TEMP
	}

	@Override
	public int size() {
		return (int) repo.count();
	}

	@Override
	public List<User> getAll() {
		return repo.findAll();
	}

	@Override
	public Boolean existsByUsername(String username) {
		return repo.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return repo.existsByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}

}
