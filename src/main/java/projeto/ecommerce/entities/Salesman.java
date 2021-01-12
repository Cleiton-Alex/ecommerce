package projeto.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "salesman" )
public class Salesman implements Serializable {

    private static final long serialVersionUID = 3960436649365666213L;

    private Long id;
    private String fullName;
    private String cpf;
    private BigInteger operation;
    private Integer accountType;
    private String phoneNumber;
    private User user;
    private String biography;
    private String photo;
    private Integer status;
    private List<Products> products = new ArrayList<>();


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "cpf", nullable = false)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    @Column(name = "operation", nullable = false)
    public BigInteger getOperation() {
        return operation;
    }

    public void setOperation(BigInteger operation) {
        this.operation = operation;
    }
    @Column(name = "account_type", nullable = false)
    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
    @Column(name = "phone_number", nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    @Column(name = "biography", nullable = false)
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @OneToOne(mappedBy = "salesman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "photo", nullable = false)
    public String getphoto() {
        return photo;
    }

    public void setphoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "salesman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesman salesman = (Salesman) o;
        return Objects.equals(id, salesman.id) &&
                Objects.equals(fullName, salesman.fullName) &&
                Objects.equals(cpf, salesman.cpf) &&
                Objects.equals(operation, salesman.operation) &&
                Objects.equals(accountType, salesman.accountType) &&
                Objects.equals(phoneNumber, salesman.phoneNumber) &&
                Objects.equals(user, salesman.user) &&
                Objects.equals(biography, salesman.biography) &&
                Objects.equals(photo, salesman.photo) &&
                Objects.equals(status, salesman.status) &&
                Objects.equals(products, salesman.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, cpf, operation, accountType, phoneNumber, user, biography, photo, status, products);
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", cpf='" + cpf + '\'' +
                ", operation=" + operation +
                ", accountType=" + accountType +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", user=" + user +
                ", biography='" + biography + '\'' +
                ", photo='" + photo + '\'' +
                ", status=" + status +
                ", products=" + products +
                '}';
    }
}

