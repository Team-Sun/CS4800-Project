package TeamSun.CS4800Project.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class HomeController {
	
	@GetMapping("/new_URL")
	public String getPage() {
		return "something simple";
	}

	@GetMapping("/something_URL")
	public String getThing() { return "I am returning a thing."; }
	
	@GetMapping("/another_URL")
	public String getSomething() { return "I am an object."; }
	
}
