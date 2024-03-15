package di.repository.telephone;

import di.model.entity.telephone.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    boolean existsByNumber(String number);

    @Query("SELECT t FROM  Telephone t WHERE t.number = :number")
    Optional<Telephone> getTelephoneByNumber(@Param("number") String number);
}
