package onlineroomrent.dao.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Table(name="property_category")
@Entity
@Getter
@Setter
public class PropertyCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false,unique = true)
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String description;
    @Column(nullable = false,unique = true)
    private String slugName;
    private String userType;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id",referencedColumnName = "id")
    private AdminEntity admin;

    @PrePersist
    public void prePersists(){
        this.createdAt=new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt=new Date();
    }
}
