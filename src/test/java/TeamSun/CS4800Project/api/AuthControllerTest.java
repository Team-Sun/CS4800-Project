package TeamSun.CS4800Project.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import TeamSun.CS4800Project.request.LoginRequest;
import TeamSun.CS4800Project.services.UserService;

/*
 * https://stackoverflow.com/questions/39865596/difference-between-using-mockmvc-with-springboottest-and-using-webmvctest
 * We could use @SpringBootTest, but it's much slower than @WebMvcTest, which does 'slicing' and only loads what we are testing.
 * 
 * NVM, @WebMvcTest is difficult to deal with.
 * TODO LOOKAT again later.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

	@Autowired
	TestRestTemplate template;

	@Autowired
	UserService userService;

	@Test
	void testLoginAuthentication() throws Exception {
		LoginRequest test = new LoginRequest();
		test.setPassword("admin");
		test.setUsername("admin");
		Assertions.assertFalse(userService.find("admin").isAuthenticated(), "User admin isn't authenticated?");
		template.postForObject("/api/auth/signin", test, String.class);
		Assertions.assertTrue(userService.find("admin").isAuthenticated(), "User admin is authenticated?");
	}
	
	@Test
	void testFailAuthentication() throws Exception {
		Assertions.assertTrue(userService.find("doesntexist1") == null, "User 'doesntexist' exists?");
		LoginRequest test = new LoginRequest();
		test.setPassword("doesntexist1");
		test.setUsername("doesntexist1");
		String response = template.postForObject("/api/auth/signin", test, String.class);
		Assertions.assertTrue(response.equals("Incorrect Username or Password."), "User 'doesntexist' exists?");
	}



}
