package onlineroomrent.dao.entity.country;

import lombok.Getter;
import lombok.Setter;
import onlineroomrent.dao.entity.BaseEntity;

import javax.persistence.*;

@Table(name="state")
@Entity
@Getter
@Setter
public class StateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;
    @ManyToOne
    @JoinColumn(name="country_id")
    private CountryEntity countryEntity;
}
