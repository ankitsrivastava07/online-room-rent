package onlineroomrent.dao.entity;

import lombok.Getter;
import lombok.Setter;
import onlineroomrent.constant.OnlineRoomRentConstant;
import onlineroomrent.dao.repository.AdminRepository;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
@MappedSuperclass
@Getter
@Setter
@SqlResultSetMapping(name="")
public class BaseEntity {
   // @Autowired
   // private AdminRepository adminRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private Long createdBy;
    private Long updatedBy;

    @PrePersist
    public void prePersists(){
        this.createdAt=new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt=new Date();
    }
}
