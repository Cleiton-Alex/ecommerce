package projeto.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.ecommerce.entities.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    Categories findByName (String name);


}
