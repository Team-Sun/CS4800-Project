package TeamSun.CS4800Project.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial") // Pretty sure we don't need a serialVersionUID
@Document(collection = "users")
public class User implements UserDetails {
	//TODO see if serialVersionUID necessary.
	
	@Id
	private ObjectId id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	//@DBRef
	private Set<SimpleGrantedAuthority> roles = new HashSet<>();

	public User() {
	}
	
	//DEBUG TODO remove
	public User(String name) {
		this.username = name;
		//TEMP TODO actually implement getter/setter
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<SimpleGrantedAuthority> getRoles() {
		return roles;
	}
	
	public Set<String> getStringRoles() {
		Set<String> result = new HashSet<>();
		for (GrantedAuthority role : roles) {
			result.add(role.toString());
		}
		return result;
	}

	public void setRoles(List<String> roles) {
		for (String role: roles) {
			this.roles.add(new SimpleGrantedAuthority(role));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}