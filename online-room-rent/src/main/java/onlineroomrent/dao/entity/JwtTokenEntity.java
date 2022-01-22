package onlineroomrent.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name="sessions")
public class JwtTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String accessToken;
    @Column(length = 1000)
    private String refreshToken;
    private Long userId;
    private String userType;
    private Date createdAt;
    private Date updatedAt;
    private String tokenIdentity;
    private Boolean active=Boolean.TRUE;
    private Date expireAt;
    private String userName;

    @PrePersist
    public void prePersists(){
        this.createdAt=new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt=new Date();
    }

}
