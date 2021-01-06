package projeto.ecommerce.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projeto.ecommerce.entities.User;
import projeto.ecommerce.security.JwtUserFactory;
import projeto.ecommerce.services.UserService;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userService.buscarPorUser(userName);

        if(user.isPresent()){
            return JwtUserFactory.create(user.get());
        }
        throw new UsernameNotFoundException("User não encontrado");
    }
}
