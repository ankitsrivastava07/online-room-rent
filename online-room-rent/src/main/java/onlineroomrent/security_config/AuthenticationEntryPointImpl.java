package onlineroomrent.security_config;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@ControllerAdvice
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Integer status=response.getStatus();
        String uri=request.getRequestURI();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

   @ExceptionHandler(value = { Exception.class })
    public void commence(HttpServletRequest request, HttpServletResponse response, Exception exception ) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        exception.printStackTrace();
        String uri=request.getRequestURI();
        response.getOutputStream().println("{ \"error\": \"" + exception.getMessage() + "\" }");
    }
}
