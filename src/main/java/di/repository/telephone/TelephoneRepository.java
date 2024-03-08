package di.repository.telephone;

import di.model.entity.user.telephone.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
}
