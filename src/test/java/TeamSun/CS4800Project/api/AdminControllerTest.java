package TeamSun.CS4800Project.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import TeamSun.CS4800Project.model.Person;
import TeamSun.CS4800Project.service.PersonService;

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
	PersonService personService;

	@Test
	void testInsertEntry() throws Exception {
		template.postForObject("/admin/insert", new Person("TestName"), String.class);
		Assertions.assertTrue(personService.findByFirstName("TestName").size() == 1, "1 Person matching TestName?");
		personService.delete(personService.findByFirstName("TestName").get(0));
		Assertions.assertTrue(personService.findByFirstName("TestName").size() == 0, "Person TestName removed?");
	}

}
