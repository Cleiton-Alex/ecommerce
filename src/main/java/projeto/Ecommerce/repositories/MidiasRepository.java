package projeto.Ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.Ecommerce.entities.Salesman;

public interface MidiasRepository extends JpaRepository<Salesman, Long> {
    Salesman findByDeletadoMidias(String deleted);
}
