/*
package onlineroomrent.security_config.filter;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        String uri=httpServletRequest.getRequestURI();
        Integer status=httpServletResponse.getStatus();
        System.out.println("/filter"+uri);
        if(status==401 && uri.equals("/api/v1/admin/add-category")) {
            //httpServletResponse.sendRedirect("/admin/login");
            filterChain.doFilter(servletRequest,servletResponse);
            //return;
        }
         filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
*/
