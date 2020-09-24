package TeamSun.CS4800Project.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * For specifically submitting notes.
 * 
 * @author Andrew
 *
 */
@RequestMapping("/submit")
@RestController
public class SubmissionController {

	@GetMapping()
	public String getPage() {

		return null;
	}

	@PostMapping()
	public String submitNotes() {

		return null;
	}
}
