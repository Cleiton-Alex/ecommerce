package projeto.ecommerce.dtos;

import projeto.ecommerce.entities.Categories;
import projeto.ecommerce.entities.Salesman;

import java.util.Arrays;

public class ProductsDto {


    private Long id;
    private Categories categories;
    private Salesman salesman;
    private  String nameProduct;
    private String description;
    private Integer value;
    private Integer status;
    private Integer stock;
    private String photo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Override
    public String toString() {
        return "ProductsDto{" +
                "id=" + id +
                ", categories=" + categories +
                ", salesman=" + salesman +
                ", nameProduct='" + nameProduct + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", status=" + status +
                ", stock=" + stock +
                ", photo='" + photo + '\'' +
                '}';
    }
}
