package onlineroomrent.dao.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
@Table(name="users_authorities")
@Entity
@Getter
@Setter
    public class UserGrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String description;
    private Boolean active;
    private String accessRights;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by",referencedColumnName = "id")
    AdminEntity adminEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role",referencedColumnName = "id")
    private Role role;

    @PrePersist
    public void prePersists(){
        this.createdAt=new Date();
        this.updatedAt=new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt=new Date();
    }
}
