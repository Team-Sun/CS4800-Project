package TeamSun.CS4800Project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.Person;
import TeamSun.CS4800Project.service.PersonService;

@RequestMapping("/admin")
@RestController
public class AdminController {

	@Autowired
	private PersonService personService;
	
	@PostMapping("/add")
	public String addEntry(@ModelAttribute("name") String name) {
		personService.insert(new Person(name));
		return "inserting " + name;
	}
	
	@GetMapping("/find")
	public Person getAllEntries(@ModelAttribute("name") String name) {
		return personService.findByFirstName(name).get(0);
	}

	@GetMapping("/getall")
	public String getAllEntries() {
		return personService.toString();
	}
	
	/* 
	 * reference: https://stackoverflow.com/questions/21824012/spring-modelattribute-vs-requestbody for @RequestBody
	 * Essentially, @RequestBody parses sent JSON and turns it into applicable given class (in this case, 'Person').
	 * 
	 * Example JSON:
	 * 
	 * {
	 * "firstName":"UniqueFirstName",
	 * "lastName":"UniqueLastName",
	 * "userName":"UniqueUserName",
	 * "password":"SomePassword"
	 * }
	 * 
	 */
	@PostMapping("/insert")
	public Person insertEntry(@RequestBody Person person) {
		personService.insert(person);
		return person;
	}
	
}
