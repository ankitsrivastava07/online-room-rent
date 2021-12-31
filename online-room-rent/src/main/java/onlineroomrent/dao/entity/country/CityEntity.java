package onlineroomrent.dao.entity.country;

import lombok.Getter;
import lombok.Setter;
import onlineroomrent.dao.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "city")
@Entity
@Getter
@Setter
public class CityEntity extends BaseEntity {
    private String name;
    @ManyToOne
    @JoinColumn(name="state_id")
    private StateEntity stateEntity;
    
}
