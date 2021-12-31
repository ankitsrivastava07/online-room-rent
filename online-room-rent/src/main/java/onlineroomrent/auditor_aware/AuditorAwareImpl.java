package onlineroomrent.auditor_aware;

import onlineroomrent.dao.entity.AdminEntity;
import onlineroomrent.dao.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<AdminEntity> {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Optional<AdminEntity> getCurrentAuditor() {
        AdminEntity adminEntity=adminRepository.findByUserType("SYSTEM_ADMIN");
        Optional<AdminEntity> optional= Optional.of(adminEntity);
        return optional;
    }
}
