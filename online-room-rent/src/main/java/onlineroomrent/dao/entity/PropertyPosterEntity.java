package onlineroomrent.dao.entity;

import lombok.Getter;
import lombok.Setter;
import onlineroomrent.constant.OnlineRoomRentConstant;

import javax.persistence.*;
import java.util.Date;

@Table(name="property_poster")
@Entity
@Getter
@Setter
public class PropertyPosterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Boolean isPopular=Boolean.FALSE;
    @Column(nullable = false)
    private String description;
    private String sqrtFit;
    private String furnished;
    private String title;
    private Integer numberOfRooms;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name="address_id")
    private AddressEntity addressEntity;

    @ManyToOne
    @JoinColumn(name="property_category_id")
    private PropertyCategoryEntity categoryEntity;

    @PrePersist
    public void prePersists(){
        this.createdAt=new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt=new Date();
    }

}
