package projeto.ecommerce.services;

import projeto.ecommerce.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * @param user
     * @return Salesman
     * */
    User pesistir(User user);

    Optional<User> buscarPorEmail(String email);


    Optional<User> buscarPorId(Long id);

    Optional<User> buscarPorUser(String userName);

    void remover(Long id);

    List<User> buscarTodos();
}
