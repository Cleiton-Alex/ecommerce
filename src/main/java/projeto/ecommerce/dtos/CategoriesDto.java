package projeto.ecommerce.dtos;

import projeto.ecommerce.entities.Categories;
import projeto.ecommerce.entities.Salesman;

import java.util.Arrays;

public class CategoriesDto {


    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "CategoriesDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
