package onlineroomrent;

import onlineroomrent.security_config.filter.AdminWebControllerFilter;
import onlineroomrent.security_config.filter.RequestAcessFilter;
import onlineroomrent.security_config.filter.TokenValidatorFilter;
import onlineroomrent.service.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class OnlineRoomRentApplication {
	@Autowired
	FrontendService frontendService;
	public static void main(String[] args) {
		SpringApplication.run(OnlineRoomRentApplication.class, args);
	}

	/*@Bean
	@Qualifier
	public FilterRegistrationBean requestAcessFilter(){
		FilterRegistrationBean filterRegistrationBean= new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new RequestAcessFilter(frontendService));
		filterRegistrationBean.addUrlPatterns("/api/v1/admin/*");
		return filterRegistrationBean;
	}
*/
	@Bean
	public FilterRegistrationBean adminWebFilter(){
		FilterRegistrationBean filterRegistrationBean= new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new AdminWebControllerFilter(frontendService));
		filterRegistrationBean.addUrlPatterns("/admin/*");
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean tokenValidatorResolverFilter(){
		FilterRegistrationBean filterRegistrationBean= new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new TokenValidatorFilter(frontendService));
		filterRegistrationBean.addUrlPatterns("/api/v1/admin/*");
		return filterRegistrationBean;
	}
}
