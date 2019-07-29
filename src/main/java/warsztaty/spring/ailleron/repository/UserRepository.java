package warsztaty.spring.ailleron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import warsztaty.spring.ailleron.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /*On jest na tyle madry, ze sam wie czego szukać po nazwie metody o_0
    * Opcjonalnie sami mozemy napisac SQLowe query*/
//    @Query("SELECT u FROM User WHERE u.name = :name")
    Optional<User> findUserByName(@Param("name") String name);
}
