package com.ecommerce.entities;

import com.ecommerce.enums.PerfilEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user" )
public class User implements Serializable {

    private static final long serialVersionUID = 5754246207015712518L;
    private Long id;
    private Salesman salesman;
    private String senha;
    private PerfilEnum perfil;
    private String email;
    private String userName;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    @Column(name = "senha", nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }

    @Column(name ="email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "userName", nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(salesman, user.salesman) &&
                Objects.equals(senha, user.senha) &&
                perfil == user.perfil &&
                Objects.equals(email, user.email) &&
                Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salesman, senha, perfil, email, userName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", salesman=" + salesman +
                ", senha='" + senha + '\'' +
                ", perfil=" + perfil +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
