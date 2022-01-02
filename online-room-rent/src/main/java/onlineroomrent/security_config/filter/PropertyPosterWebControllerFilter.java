package onlineroomrent.security_config.filter;

import onlineroomrent.dao.entity.JwtTokenEntity;
import onlineroomrent.dto.TokenStatus;
import onlineroomrent.service.FrontendService;
import onlineroomrent.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyPosterWebControllerFilter implements Filter {

    @Autowired
    FrontendService frontendService;

    public PropertyPosterWebControllerFilter(FrontendService frontendService){
        this.frontendService=frontendService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse)servletResponse;
        String uri=httpServletRequest.getRequestURI();

        Cookie cookies[] = httpServletRequest.getCookies();
        if(cookies==null && !(uri.equals("/property-owner/login") || uri.equals("/property-owner/register"))){
            httpServletResponse.sendRedirect("/property-owner/login");
            return;
        }
        List<String> list=new ArrayList<>();
        if(cookies!=null)
          list= Arrays.stream(cookies).filter(cookie->cookie.getName().equals("session_Token")).map(Cookie::getValue).collect(Collectors.toList());
        String jwt=list.size()==0?null:list.get(0);
        if (cookies!=null && (jwt==null || jwt!=null) && !isSessionExpired(jwt) && !(uri.equals("/property-owner/login") || uri.equals("/property-owner/register"))) {
            if(!isSessionExpired(jwt)) {
                httpServletResponse.sendRedirect("/property-owner/login");
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private boolean isSessionExpired(String jwt){
        JwtTokenEntity entity=null;
        if(jwt==null)
            return false;
        else if ((entity=frontendService.isValidToken(jwt))!=null){
            TokenStatus tokenStatus = new TokenStatus();
            tokenStatus.setStatus(entity.getActive());
            tokenStatus.setAccessToken(entity.getAccessToken());
            tokenStatus.setUserName(entity.getUserName());
            TenantContext.setTenantContext(tokenStatus);
            return true;
        }
        return false;
    }

}
