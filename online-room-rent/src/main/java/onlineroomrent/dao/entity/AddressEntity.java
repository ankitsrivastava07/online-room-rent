package onlineroomrent.dao.entity;

import lombok.Getter;
import lombok.Setter;
import onlineroomrent.dao.entity.country.CityEntity;
import onlineroomrent.dao.entity.country.CountryEntity;
import onlineroomrent.dao.entity.country.StateEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="property_addess")
@Entity
@Getter
@Setter
public class AddressEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="city_id")
    private CityEntity cityEntity;
    @ManyToOne
    @JoinColumn(name="state_id")
    private StateEntity stateEntity;
    @ManyToOne
    @JoinColumn(name="country_id")
    private CountryEntity countryEntity;
}
