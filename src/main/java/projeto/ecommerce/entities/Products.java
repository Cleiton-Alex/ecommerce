package projeto.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Products implements Serializable {

    private static final long serialVersionUID = 7754246207015712518L;
    private Long id;
    private Categories categories;
    private Salesman salesman;
    private  String name;
    private String description;
    private Integer value;
    private Integer status;
    private Integer stock;
    private String photo;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    public Salesman getSalesman() {
        return salesman;
    }
    @JsonIgnore
    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    @Column(name = "name", nullable = false)
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "value", nullable = false)
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "stock", nullable = false)
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Column(name = "photo", nullable = false)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(id, products.id) &&
                Objects.equals(categories, products.categories) &&
                Objects.equals(salesman, products.salesman) &&
                Objects.equals(name, products.name) &&
                Objects.equals(description, products.description) &&
                Objects.equals(value, products.value) &&
                Objects.equals(status, products.status) &&
                Objects.equals(stock, products.stock) &&
                Objects.equals(photo, products.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categories, salesman, name, description, value, status, stock, photo);
    }


    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", categories=" + categories +
                ", salesman=" + salesman +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", status=" + status +
                ", stock=" + stock +
                ", photo='" + photo + '\'' +
                '}';
    }
}
