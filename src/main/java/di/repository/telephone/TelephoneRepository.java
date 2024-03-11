package di.repository.telephone;

import di.model.entity.telephone.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    boolean existsByNumber(String number);
}
