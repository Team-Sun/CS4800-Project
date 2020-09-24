package TeamSun.CS4800Project.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import com.google.common.base.Joiner;

import org.apache.commons.io.IOUtils;
import org.apache.commons.math3.fraction.Fraction;
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

	@GetMapping("/guava")
	public String guava() {
		Joiner joiner = Joiner.on(" and ").skipNulls();
		return joiner.join("iPad", null, "iPhone", "Apple Watch");
	}

	@GetMapping("/commons-math")
	public Double mathIsFun()
	{
		Fraction f = new Fraction(1,2);
		return f.doubleValue();
	}

	@GetMapping("/IOExample")
	public String IOExample() throws MalformedURLException, IOException
	{
		InputStream in = new URL( "https://commons.apache.org" ).openStream();
	 	try {
	   		return( IOUtils.toString( in, "UTF-8" ) );
	 	} finally {
	   		IOUtils.close(in);
	 	}
	}
}
