package onlineroomrent.security_config.filter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import onlineroomrent.constant.OnlineRoomRentConstant;
import onlineroomrent.dao.entity.JwtTokenEntity;
import onlineroomrent.dto.TokenStatus;
import onlineroomrent.jwtUtil.JwtAccessTokenUtil;
import onlineroomrent.service.FrontendService;
import onlineroomrent.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
@Component
public class AdminWebControllerFilter implements Filter {
    @Autowired
    FrontendService frontendService;
    @Autowired
    JwtAccessTokenUtil jwtAccessTokenUtil;
    public AdminWebControllerFilter(FrontendService frontendService){
        this.frontendService=frontendService;
    }
    @Override
    @CacheEvict(cacheNames = "session_Token",allEntries = true)
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String uri = httpServletRequest.getRequestURI();
        Cookie cookies[] = httpServletRequest.getCookies();
        List<String>token=null;
        if(cookies==null && !(uri.equals("/admin") || uri.equals("/admin/login"))){
            httpServletResponse.sendRedirect("/admin/login");
            return;
        }
        List<String>list=new ArrayList<>();
        if(cookies!=null)
        list=Arrays.stream(cookies).filter(cookie->cookie.getName().equals("session_Token")).map(Cookie::getValue).collect(Collectors.toList());
        String jwt=list.size()==0?null:list.get(0);
        if (cookies!=null && (jwt==null || jwt!=null) && !isSessionExpired(jwt) && !((uri.equals("/admin") || uri.equals("/admin/login")))) {
            if(!isSessionExpired(jwt)) {
                httpServletResponse.sendRedirect("/admin/login");
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private boolean isSessionExpired(String jwt){
        JwtTokenEntity entity=null;
        if(jwt==null)
            return false;
       else if (frontendService.isValidToken(jwt, OnlineRoomRentConstant.Admin_TOKEN_KEY)){
           TokenStatus tokenStatus = new TokenStatus();
           tokenStatus.setStatus(Boolean.TRUE);
           tokenStatus.setAccessToken(jwt);
           tokenStatus.setUserName(frontendService.findById(jwt));
           TenantContext.setTenantContext(tokenStatus);
           return true;
        }
        return false;
    }

    public String getTokenIdentity(String accessToken,String value){
        try {
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String[] parts = accessToken.split("\\."); // Splitting header, payload and signature
            String payload = new String(decoder.decode(parts[1]));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(payload, Map.class);
            return String.valueOf(map.get(value));
        }catch (JsonProcessingException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
