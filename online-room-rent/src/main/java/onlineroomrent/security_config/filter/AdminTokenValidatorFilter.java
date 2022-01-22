package onlineroomrent.security_config.filter;
import onlineroomrent.constant.OnlineRoomRentConstant;
import onlineroomrent.dao.entity.JwtTokenEntity;
import onlineroomrent.dto.TokenStatus;
import onlineroomrent.service.FrontendService;
import onlineroomrent.tenant.TenantContext;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
public class AdminTokenValidatorFilter implements Filter {
    FrontendService frontendService;
    public AdminTokenValidatorFilter(FrontendService frontendService){
        this.frontendService=frontendService;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse)servletResponse;
        String uri=httpServletRequest.getRequestURI();
        String jwt=httpServletRequest.getHeader("Authentication");
        if(!uri.equals("/api/v1/admin/otp-verify") && !isSessionExpired(jwt,httpServletRequest) && !(uri.equals("/api/v1/admin/login"))) {
            httpServletResponse.setContentType("application/json");
            PrintWriter writer = httpServletResponse.getWriter();
            Map<String,Object> map = new HashMap<>();
            map.put("status",Boolean.FALSE);
            map.put("message", OnlineRoomRentConstant.SESSION_EXPIRED_DEFAULT_MESSAGE);
            map.put("errorCode", HttpStatus.UNAUTHORIZED.name());
            JSONObject jsonObject = new JSONObject(map);
            writer.print(jsonObject.toString());
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    private boolean isSessionExpired(String jwt,HttpServletRequest request){
        String uri=request.getRequestURI();
        uri.contains("/api/v1/admin");
        JwtTokenEntity entity=null;
        TokenStatus tokenStatus = new TokenStatus();
        if(jwt==null)
            return false;
        else if (frontendService.isValidToken(jwt,OnlineRoomRentConstant.Admin_TOKEN_KEY)){
            tokenStatus.setStatus(Boolean.TRUE);
            tokenStatus.setAccessToken(jwt);
            tokenStatus.setUserName(frontendService.findById(jwt));
            TenantContext.setTenantContext(tokenStatus);
            return true;
        }
        TenantContext.setTenantContext(tokenStatus);
        return false;
    }

}
