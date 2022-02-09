package onlineroomrent;
import onlineroomrent.dao.repository.AdminRepository;
import onlineroomrent.security_config.filter.AdminTokenValidatorFilter;
import onlineroomrent.security_config.filter.AdminWebControllerFilter;
import onlineroomrent.security_config.filter.PropertyAdsWebControllerFilter;
import onlineroomrent.security_config.filter.TokenValidatorFilter;
import onlineroomrent.service.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class OnlineRoomRentApplication {
	@Autowired
	FrontendService frontendService;
	@Autowired AdminRepository adminRepository;
	public OnlineRoomRentApplication(AdminRepository adminRepository){
		this.adminRepository=adminRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineRoomRentApplication.class,args);
	}
	@Bean
	public FilterRegistrationBean postPropertyFilter(){
		FilterRegistrationBean filterRegistrationBean= new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new PropertyAdsWebControllerFilter(frontendService));
		filterRegistrationBean.addUrlPatterns("/property-owner/*");
		return filterRegistrationBean;
	}

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
		filterRegistrationBean.addUrlPatterns("/api/v1/property/*");
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean adminTokenValidatorFilter(){
		FilterRegistrationBean filterRegistrationBean= new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new AdminTokenValidatorFilter(frontendService));
		filterRegistrationBean.addUrlPatterns("/api/v1/admin/*");
		return filterRegistrationBean;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(5242880);
		return multipartResolver;
	}

/*
	Programming Languages and Frameworks – Hands on experience in some or all of the following is preferred:
	Java, Python, Go, React, Envoy, gRPC, ProtoBuf, JSON, CouchBase, Cassandra, Redis, Consul, Jenkins, Docker, Kubernetes, OpenShift, Drools, Elastic Stack, Kafka, Spark
*/
}
