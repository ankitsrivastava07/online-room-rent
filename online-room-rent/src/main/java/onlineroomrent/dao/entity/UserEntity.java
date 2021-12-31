package onlineroomrent.dao.entity;

import lombok.Getter;
import lombok.Setter;
import onlineroomrent.encryptUtil.EncryptUtil;

import javax.persistence.*;

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
    private String mobile;
    private String password;
    private Boolean isMobileVarified=Boolean.FALSE;
    private Boolean isEmailVarified=Boolean.FALSE;
    private Boolean active=Boolean.TRUE;
    private String userType;

    @PrePersist
    public void passwordEncode(){
        this.password= EncryptUtil.encrypt(this.password);
    }

}
