package di.repository.user;

import di.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM  User u WHERE u.telephone.number = :number")
    User getByTelephone(@Param("number") String telephone);
}
