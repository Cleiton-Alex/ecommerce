package projeto.ecommerce.services;

import projeto.ecommerce.entities.Salesman;

import java.util.List;
import java.util.Optional;

public interface SalesmanService {

    /**
     * @param salesman
     * @return Salesman
     * */
    Salesman pesistir(Salesman salesman);

    Optional<Salesman> buscarPorCpf(String cpf);


    Optional<Salesman> buscarPorId(Long id);

    Optional<Salesman> buscarPorStatus(Integer status);

    void remover(Long id);

    List <Salesman> buscarTodos();

    Optional<Salesman> buscarSalesmanUser(Long id);


}
