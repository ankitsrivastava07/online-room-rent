package onlineroomrent.dao.entity.country;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Table(name="country")
@Entity
@Getter
@Setter
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    @Column(name="country_code")
    private String countryCode;
    @Column(name="phone_code")
    private String phoneCode;
}
