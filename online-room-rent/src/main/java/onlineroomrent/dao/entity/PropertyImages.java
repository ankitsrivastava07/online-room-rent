package onlineroomrent.dao.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Getter
@Setter
@Table(name="images")
public class PropertyImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String fileName;
    private String contentType;
    private String bucketName;
    private String bucketUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="property_id")
    private PropertyAdsEntity propertyAds;
}
