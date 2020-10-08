package TeamSun.CS4800Project.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import TeamSun.CS4800Project.model.ERole;
import TeamSun.CS4800Project.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}