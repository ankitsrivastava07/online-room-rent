package onlineroomrent.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Table(name="admin")
@Entity
@Getter
@Setter
public class AdminEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private Boolean active=true;
    @Column(name="failed_attempt")
    private Integer failedAttempt=3;
    @Column(nullable = false, name="first_name")
    private String firstName;
    @Column(nullable = false,name="last_name")
    private String lastName;
    @Column(name="date_non_block")
    private Date nonBlockDate;
    @Column(nullable = false,name="is_block")
    private Boolean isBlock=Boolean.FALSE;
    @Column(nullable = false,name="max_attempt")
    private Integer maxAttempt=3;
    @Column(nullable = false,name="email")
    private String email;
    @Column(nullable = false,name="mobile")
    private String mobile;
    @Column(nullable = false,name="password")
    private String password;
    @Column(nullable = false,name="user_type")
    private String userType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="role",nullable = false)
    private Role role;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @PrePersist
    public void prePersit(){
        this.createdAt=new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt=new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities= new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(this.userType));
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isBlock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isBlock;
    }

    @Override
    public boolean isEnabled() {
        return this.isBlock;
    }
}
