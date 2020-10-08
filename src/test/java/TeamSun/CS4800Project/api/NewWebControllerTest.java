package TeamSun.CS4800Project.api;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class NewWebControllerTest {
	private NewWebController nwc = new NewWebController();
	private DateTime dt = new DateTime();
	
	// This is a test 
	@Test
	public void testResult() {
		boolean res = nwc.isAfterPayDay(dt);
		Assert.assertEquals(dt.getDayOfMonth(), res);
	}
}
