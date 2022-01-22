package onlineroomrent.dao.entity;
import lombok.Getter;
import lombok.Setter;
import onlineroomrent.dao.entity.country.CityEntity;
import onlineroomrent.dao.entity.country.CountryEntity;
import onlineroomrent.dao.entity.country.StateEntity;
import onlineroomrent.dao.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
@Table(name="address")
@Entity
@Getter
@Setter
public class AddressEntity extends BaseEntity{
    @OneToOne
    @JoinColumn(name="city_id")
    private CityEntity city;
    @OneToOne
    @JoinColumn(name="state_id")
    private StateEntity state;
    @OneToOne
    @JoinColumn(name="country_id")
    private CountryEntity country;
    @Column(name="address",nullable = false)
    private String address;
    private String pinCode;
}
