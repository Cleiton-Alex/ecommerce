package projeto.ecommerce.security.dto;

import javax.validation.constraints.NotEmpty;

public class JwtAuthenticationDto {
    private String user;
    private String senha;


    @NotEmpty(message = "Usuario não pode ser vazio")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @NotEmpty(message = "senha não pode ser vazia")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationDto{" +
                "user='" + user + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
