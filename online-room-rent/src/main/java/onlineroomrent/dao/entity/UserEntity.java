package onlineroomrent.dao.entity;
import lombok.Getter;
import lombok.Setter;
import onlineroomrent.encryptUtil.EncryptUtil;
import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
public class UserEntity extends BaseEntity{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="role",referencedColumnName = "id")
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    @Column(unique = true)
    private String mobile;
    @Column(unique = true)
    private String alternateMobile;
    private String password;
    private Boolean isMobileVarified=Boolean.FALSE;
    private Boolean isEmailVarified=Boolean.FALSE;
    private Boolean active=Boolean.TRUE;
    private String userType;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userId")
    @Column(name="properties")
    private List<PropertyAdsEntity> properties;
    @PrePersist
    public void passwordEncode(){
        this.password= EncryptUtil.encrypt(this.password);
    }

}
