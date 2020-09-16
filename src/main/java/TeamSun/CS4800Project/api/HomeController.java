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
	
}
