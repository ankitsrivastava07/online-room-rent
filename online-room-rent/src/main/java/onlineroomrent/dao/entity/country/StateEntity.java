package onlineroomrent.dao.entity.country;

import lombok.Getter;
import lombok.Setter;
import onlineroomrent.dao.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="state")
@Entity
@Getter
@Setter
public class StateEntity extends BaseEntity {
    private String name;
    @ManyToOne
    @JoinColumn(name="country_id")
    private CountryEntity countryEntity;
    private String countryCode;
}
