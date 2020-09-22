package TeamSun.CS4800Project.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/mai_URL")
	public String getURL() { return "Return :D"; }


	@GetMapping("/nicky_URL")
	public String getAnotherThing() { return "Hello world!"; }
}
