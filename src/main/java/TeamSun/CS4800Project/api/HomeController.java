package TeamSun.CS4800Project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TeamSun.CS4800Project.model.TestModel;
import TeamSun.CS4800Project.repositories.TestRepo;

@RequestMapping
@RestController
public class HomeController {
	
	@Autowired
	private TestRepo repo;

	@GetMapping("/new_URL")
	public String getPage() {
		return "something simple";
	}

	@GetMapping("/something_URL")
	public String getThing() {
		return "I am returning a thing.";
	}
	
	@PostMapping("/add")
	public String addEntry() {
		repo.save(new TestModel("randomname"));
		return "works";
	}
	
	@GetMapping("/getall")
	public String getAllEntries() {
		return repo.findAll().toString();
	}

}
