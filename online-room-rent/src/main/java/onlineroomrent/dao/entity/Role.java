package onlineroomrent.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="role")
@Getter
@Setter
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false,unique = true)
    private String slugName;
    private String name;
    @Column(nullable = false)
    private Boolean active;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "role",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @Column(nullable = false,name="authorities")
    private List<UserGrantedAuthority> authorities;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="created_by",referencedColumnName = "id")
    AdminEntity adminEntity;
    private Date createdAt;
    private Date updatedAt;

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
