package TeamSun.CS4800Project.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping
	public String getPage() {
		return "home";
	}
	
}
