package di.repository.user;

import di.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM  User u WHERE u.telephone.number = :number")
    User getByTelephone(@Param("number") String telephone);
    @Query("SELECT u FROM  User u WHERE u.name = :name")
    Optional<User> getByUserName(@Param("name") String name);

}
