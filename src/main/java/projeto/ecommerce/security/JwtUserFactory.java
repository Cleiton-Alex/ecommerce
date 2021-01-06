package projeto.ecommerce.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import projeto.ecommerce.entities.User;
import projeto.ecommerce.enums.PerfilEnum;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    /**
     * Converte e gera um JwtUser com base nos dados de um funcionário.
     *
     * @param user
     * @return JwtUser
     */
    public static JwtUser create(User user) {
        return new JwtUser(user.getId(), user.getEmail(), user.getSenha(),
                mapToGrantedAuthorities(user.getPerfil()));
    }

    /**
     * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
     *
     * @param perfilEnum
     * @return List<GrantedAuthority>
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
        return authorities;
    }
}
