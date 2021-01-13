package projeto.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import projeto.ecommerce.entities.Salesman;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Transactional(readOnly = true)
@NamedQueries({
        @NamedQuery(name = "ProductsRepository.findByCategoriesOrNameProduct",

                query = "SELECT salesman  FROM Salesman salesman WHERE salesman.user.id = :userID")})


public interface SalesmanRepository extends JpaRepository<Salesman, Long> {

    Salesman findByFullName(String fullName);
    Salesman findByCpf(String cpf);

    Salesman findByStatus(Integer status);

    Salesman findByUser_Id(Long userID);


}
