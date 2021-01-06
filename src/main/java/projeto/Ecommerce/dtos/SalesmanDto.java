package projeto.ecommerce.dtos;

import projeto.ecommerce.entities.User;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;

public class SalesmanDto {
    private Optional<Long> id = Optional.empty();
    private String fullName;
    private String cpf;
    private BigInteger operation;
    private Integer accountType;
    private String phoneNumber;
    private String email;
    private User user;
    private String biography;
    private  String selfPortrait;
    private Integer status;
    private String senha;



    public Optional<Long> getId() {
        return id;
    }

    public void setId(Optional<Long> id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigInteger getOperation() {
        return operation;
    }

    public void setOperation(BigInteger operation) {
        this.operation = operation;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getSelfPortrait() {
        return selfPortrait;
    }

    public void setSelfPortrait(String selfPortrait) {
        this.selfPortrait = selfPortrait;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "SalesmanDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", cpf='" + cpf + '\'' +
                ", operation=" + operation +
                ", accountType=" + accountType +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                ", biography='" + biography + '\'' +
                ", selfPortrait='" + selfPortrait + '\'' +
                ", status=" + status +
                ", senha='" + senha + '\'' +
                '}';
    }
}
