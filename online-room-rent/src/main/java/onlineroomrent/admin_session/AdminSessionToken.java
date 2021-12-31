package onlineroomrent.admin_session;
import onlineroomrent.jwtUtil.JwtAccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdminSessionToken {
    @Autowired RedisTemplate<String,String> redisTemplate;
    public static final String ADMIN_SESSION="ADMIN_SESSION";

    @Autowired
    JwtAccessTokenUtil jwtAccessTokenUtil;
    public static final String HASH_KEY="";
    @Cacheable()
    public String saveSession(String jwtToken){
        redisTemplate.opsForHash().put(ADMIN_SESSION,"","");
        // redisTemplate.opsForHash().get(ADMIN_SESSION,);
        return null;
    }
}
