package onlineroomrent.dao.repository;
import onlineroomrent.dao.entity.PropertyAdsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface PropertyAdsRepository extends JpaRepository<PropertyAdsEntity,Long>, JpaSpecificationExecutor<PropertyAdsEntity> {
    @Query(value="select p from PropertyAdsEntity p where p.slugName=:slugName and p.address.slugName=:addressSlug or p.address.state.state=:state")
    List<PropertyAdsEntity> findAllPropertyBySlugAndAddress(String slugName,String addressSlug,String state);
}
