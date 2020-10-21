package TeamSun.CS4800Project.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import TeamSun.CS4800Project.model.User;
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
class AdminControllerTest {

	@Autowired
	TestRestTemplate template;

	@Autowired
	UserService userService;

	@Test
	void testInsertEntry() throws Exception {
		template.postForObject("/admin/insert", new User("TestName"), String.class);
		Assertions.assertTrue(userService.find("TestName") != null, "1 Person matching TestName?");
		userService.delete(userService.find("TestName"));
		Assertions.assertTrue(userService.find("TestName") == null, "Person TestName removed?");
	}


}
