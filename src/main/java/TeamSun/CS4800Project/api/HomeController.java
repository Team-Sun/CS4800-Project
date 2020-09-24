package TeamSun.CS4800Project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.TestModel;
import TeamSun.CS4800Project.repositories.TestRepo;

/**
 * For specifically viewing basic home elements. This includes: About, FAQ, TOS,
 * etc...
 * 
 * @author Andrew
 *
 */
@RequestMapping
@RestController
public class HomeController {

	@Autowired
	private TestRepo repo;

	@PostMapping("/add")
	public String addEntry(@ModelAttribute("name") String name) {
		repo.save(new TestModel(name));
		return "inserting " + name;
	}

	@GetMapping("/getall")
	public String getAllEntries() {
		return repo.findAll().toString();
	}

}
