package projeto.ecommerce.services;

import projeto.ecommerce.entities.Salesman;

import java.util.Optional;

public interface SalesmanService {

    /**
     * @param salesman
     * @return Salesman
     * */
    Salesman pesistir(Salesman salesman);

    Optional<Salesman> buscarPorCpf(String cpf);


    Optional<Salesman> buscarPorId(Long id);


}
