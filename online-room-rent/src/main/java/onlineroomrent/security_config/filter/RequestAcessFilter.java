/*
package onlineroomrent.security_config.filter;
import onlineroomrent.service.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class RequestAcessFilter implements Filter {

    @Autowired
    FrontendService frontendService;

    public RequestAcessFilter(FrontendService frontendService){
        this.frontendService=frontendService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;
        String jwt=httpServletRequest.getHeader("Authentication");
        String browser=httpServletRequest.getHeader("browser");
        String uri=((HttpServletRequest)servletRequest).getRequestURI();
        if ((jwt!=null || jwt==null || jwt.equals("undefined")) && frontendService.isValidToken(jwt) && !uri.equals("/api/v1/admin/login")){
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"Your session has been successfully expired");
                return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
*/
