package onlineroomrent.dao.repository;

import onlineroomrent.dao.entity.JwtTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;

public interface JwtTokenRepository extends JpaRepository<JwtTokenEntity,Long> {

    //JwtTokenEntity findByTokenIdentityAndExpireAtGraterThan(String identityToken,Date createdAt);
    JwtTokenEntity findByTokenIdentity(String identityToken);
}
