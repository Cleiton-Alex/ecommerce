package projeto.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.ecommerce.entities.Salesman;

public interface SalesmanRepository extends JpaRepository<Salesman, Long> {

    Salesman findByFullName(String fullName);
    Salesman findByCpf(String cpf);


}
