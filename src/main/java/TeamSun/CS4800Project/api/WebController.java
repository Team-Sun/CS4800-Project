package TeamSun.CS4800Project.api;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class WebController {

	@GetMapping("/date")
	public String doStuff() {
		// Got help from https://www.joda.org/joda-time/quickstart.html
		DateTime time = new DateTime(new Date());
		return time.monthOfYear().getAsText() + " " + time.getDayOfMonth() + " " + time.year().getAsText();
	}
	
}
