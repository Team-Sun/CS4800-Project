package TeamSun.CS4800Project.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ServerConfig {

	
	// Thank you https://stackoverflow.com/questions/49406779/embeddedservletcontainercustomizer-in-spring-boot-2-0?rq=1
	// Only problem is that we no longer have access to the error pages.
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
	    return (test) -> test.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/index.html"));
	}
	
}
