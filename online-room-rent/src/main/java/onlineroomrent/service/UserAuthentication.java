package onlineroomrent.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Component
public class UserAuthentication implements Authentication {

    private String message;
    private Boolean status;
    private String accessToken;
    private boolean authenticated=true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
       this.authenticated=isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }

    public String hasAuthority(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> list=authentication.getAuthorities();
       String auth= list.stream().filter(role->role.getAuthority().equals("SYSTEM_ADMIN")).map(GrantedAuthority::getAuthority).findAny().get();
        return auth;
    }
}
