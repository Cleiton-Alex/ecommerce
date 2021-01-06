package projeto.ecommerce.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.ecommerce.entities.Salesman;
import projeto.ecommerce.entities.User;
import projeto.ecommerce.repositories.UserRepository;
import projeto.ecommerce.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(SalesmanServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public User pesistir(User user) {
        log.info("Persistindo user: {}", user);
        return  this.userRepository.save(user);

    }

    @Override
    public Optional<User> buscarPorEmail(String email) {
        log.info("Buscando por email: {}", email);
        return Optional.ofNullable(this.userRepository.findByEmail(email));
    }

    @Override
    public Optional<User> buscarPorId(Long id) {
        log.info("Buscando por ID: {}", id);
        return this.userRepository.findById(id);
    }

    @Override
    public Optional<User> buscarPorUser(String userName) {
        log.info("Buscando por user name: {} ", userName);
        return Optional.ofNullable(this.userRepository.findByUserName(userName));
    }
}
