package onlineroomrent.dao.entity.country;

import lombok.Getter;
import lombok.Setter;
import onlineroomrent.dao.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name="country")
@Entity
@Getter
@Setter
public class CountryEntity extends BaseEntity {
    private String name;
    private String countryCode;
    private String phonecode;
}
