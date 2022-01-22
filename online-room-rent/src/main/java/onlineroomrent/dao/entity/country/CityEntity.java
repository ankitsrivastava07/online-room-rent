package onlineroomrent.dao.entity.country;

import lombok.Getter;
import lombok.Setter;
import onlineroomrent.dao.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "city")
@Entity
@Getter
@Setter
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    @ManyToOne
    @JoinColumn(name="state_id")
    private StateEntity stateEntity;
    
}
