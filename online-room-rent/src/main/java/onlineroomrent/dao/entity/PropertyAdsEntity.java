package onlineroomrent.dao.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Table(name="property_ads")
@Entity
@Getter
@Setter
public class PropertyAdsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String propertyType;
    @Column(nullable = false)
    private Boolean isPopular=Boolean.TRUE;
    @Column(nullable = false)
    private String description;
    private String sqrtFit;
    private String furnished;
    private String title;
    private String roomSet;
    private String floor;
    private String totalFloor;
    private String bedroom;
    private String bathroom;
    private String balcony;
    private String electricity;
    private String water;
    private String tenantPreferred;
    private String rentalValue;
    private String bookingAmount;
    private String securityAmount;
    private String facilities;
    private String ageOfConstruction;
    private Date createdAt;
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "propertyAds")
    private List<PropertyImages> images;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id",referencedColumnName = "id")
    private AddressEntity address;
    @ManyToOne
    @JoinColumn(name="category_id")
    private PropertyCategoryEntity categoryEntity;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userId;
    @PrePersist
    public void prePersists(){
        this.createdAt=new Date();
    }
    @PreUpdate
    public void preUpdate(){
        this.updatedAt=new Date();
    }
}
