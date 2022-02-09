package onlineroomrent.security_config.filter;
import onlineroomrent.constant.OnlineRoomRentConstant;
import onlineroomrent.dao.entity.JwtTokenEntity;
import onlineroomrent.dto.TokenStatus;
import onlineroomrent.service.FrontendService;
import onlineroomrent.tenant.TenantContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
@Component
public class TokenValidatorFilter implements Filter {
    @Autowired
    FrontendService frontendService;
    public TokenValidatorFilter(FrontendService frontendService){
        this.frontendService=frontendService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse)servletResponse;
        String uri=httpServletRequest.getRequestURI();
        String jwt=httpServletRequest.getHeader("Authentication");
        if(!uri.equals("/api/v1/property/owner/register") && !uri.equals("/api/v1/property/login") && !isSessionExpired(jwt) && !(uri.equals("/api/v1/admin/login"))) {
            httpServletResponse.setContentType("application/json");
            PrintWriter writer = httpServletResponse.getWriter();
            Map<String,Object> map = new HashMap<>();
            map.put("status",Boolean.FALSE);
            map.put("message",OnlineRoomRentConstant.SESSION_EXPIRED_DEFAULT_MESSAGE);
            map.put("errorCode",HttpStatus.UNAUTHORIZED.name());
            JSONObject jsonObject = new JSONObject(map);
            writer.print(jsonObject.toString());
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    private boolean isSessionExpired(String jwt){
        JwtTokenEntity entity=null;
        TokenStatus tokenStatus = new TokenStatus();
        if(jwt==null)
            return false;
        else if (frontendService.isValidToken(jwt,OnlineRoomRentConstant.User_TOKEN_KEY)){
            tokenStatus.setStatus(Boolean.TRUE);
            tokenStatus.setAccessToken(jwt);
            tokenStatus.setUserName(frontendService.findById(jwt));
            tokenStatus.setUserId(frontendService.findByUserId(jwt));
            TenantContext.setTenantContext(tokenStatus);
            return true;
        }
        TenantContext.setTenantContext(tokenStatus);
        return false;
    }

}
