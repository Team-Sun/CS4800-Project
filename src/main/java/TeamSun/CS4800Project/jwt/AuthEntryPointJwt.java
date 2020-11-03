package TeamSun.CS4800Project.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
	
	@Autowired
	JwtUtils jwtUtils;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		String token = jwtUtils.parseJwt(request);
		String username = "noauth";
		if (jwtUtils.validateJwtToken(token)) {
			username = jwtUtils.getUserNameFromJwtToken(token);
		}
		
		logger.error("Unauthorized error: {}", authException.getMessage() + " " + request.getRequestURL().toString() + " " + username + " Token: " + token);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
	}

}