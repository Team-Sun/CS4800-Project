package TeamSun.CS4800Project.api;

import org.joda.time.DateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class NewWebController {
	@GetMapping("/new_link")
	public boolean isAfterPayDay(DateTime datetime) {
		if (datetime.getMonthOfYear() == 2) {   
			return datetime.getDayOfMonth() > 26;
		}
		return datetime.getDayOfMonth() > 28;
	}
}
