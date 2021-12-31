package onlineroomrent.service;

import onlineroomrent.dao.repository.AdminRepository;
import onlineroomrent.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AuthenticateUser implements AuthenticationProvider {
    @Autowired
    FrontendService frontendService;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailOrMobile = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        ApiResponse apiResponse=frontendService.login(emailOrMobile,password);
        UserAuthentication userAuthentication= new UserAuthentication();
        Authentication authentication1=new UsernamePasswordAuthenticationToken(emailOrMobile,password, adminRepository.findByEmailOrMobile(emailOrMobile).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication1);
        userAuthentication.setAccessToken(apiResponse.getAccessToken());
        userAuthentication.setStatus(apiResponse.getStatus());
        userAuthentication.setMessage(apiResponse.getMessage());
        return userAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
