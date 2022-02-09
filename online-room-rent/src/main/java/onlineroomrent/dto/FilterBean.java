package onlineroomrent.dto;
import io.netty.util.internal.StringUtil;
import lombok.Getter;
import lombok.Setter;
import onlineroomrent.dao.entity.PropertyAdsEntity;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
public class FilterBean {
    private String state;
    private String city;
    private String locality;
    private Date createdAt;
    private Double minPrice;
    private Double maxPrice;
    private String propertyCategory;
    private Integer propertyCategoryId;
    private Date upcomingProperty;
    private String roomSet;
    public Specification<PropertyAdsEntity> search(){
        Specification<PropertyAdsEntity> entitySpecification= new Specification<PropertyAdsEntity>(){
          @Override
           public Predicate toPredicate(Root<PropertyAdsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               List<Predicate> predicate= new ArrayList<>();
           if(!StringUtil.isNullOrEmpty(roomSet)) {
               predicate.add(cb.like(root.get("roomSet"), "%" + roomSet + "%"));
           }
           if(Objects.nonNull(createdAt) && createdAt.compareTo(java.sql.Date.valueOf("1900-01-01"))!=0) {
               predicate.add(cb.equal(root.<Date>get("createdAt"), createdAt));
           }
           if(!StringUtil.isNullOrEmpty(state)){
              predicate.add(cb.equal(root.get("address").get("state").get("state"),state));
           }
           Predicate predicate1 =cb.and(predicate.toArray(new Predicate[predicate.size()]));
             return predicate1;
       }
       };
        return entitySpecification;
    }
}
