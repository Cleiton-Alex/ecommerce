package projeto.ecommerce.services;

import projeto.ecommerce.entities.Categories;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {


    /**
     * @param categories
     * @return Salesman
     * */
    Categories pesistir(Categories categories);


    Optional<Categories> buscarPorId(Long id);

    Optional<Categories> buscarPorName(String name);

     List<Categories> getAll();
}
