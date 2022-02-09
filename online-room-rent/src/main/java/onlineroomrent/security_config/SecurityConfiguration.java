package onlineroomrent.security_config;
import onlineroomrent.service.AuthenticateUser;
import onlineroomrent.service.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
//@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticateUser authenticateUser;

    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    FrontendService frontendService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
      httpSecurity.httpBasic().authenticationEntryPoint(authenticationEntryPoint).and()
              .authorizeRequests()
                .antMatchers("/","/login","/home","/propertyDetail/*","/contact-us","/admin/**","/api/v1/property/**","/property-owner/**","/search-by-filter")
                .permitAll()
                .antMatchers("/api/v1/admin/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors().disable().csrf().disable();
        httpSecurity.authenticationProvider(authenticateUser);
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers( "/favicon/**","/fonts/**","/admin-ui/**","/owner-register/**","/online-room-rent/**","/online-room-rent/assets/vendor/**");
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
