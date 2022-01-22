package onlineroomrent.dao.entity;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="subscription")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="subscriptionCreateAt")
    private Date subscriptionCreateAt;
    @Column(name="subscriptionEndAt")
    private Date subscriptionEndAt;
    @ManyToOne
    private UserEntity userEntity;


}
