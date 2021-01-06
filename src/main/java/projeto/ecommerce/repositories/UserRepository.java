package projeto.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.ecommerce.entities.User;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByUserName(String name);
    User findByEmail(String email);

}
