package projeto.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import projeto.ecommerce.entities.Products;



public interface ProductsRepository extends JpaRepository<Products, Long> {

   Page<Products> findByCategories(@Param("categoriesId") Long categoriesId, Pageable pageable);
   Page<Products> findBySalesman(@Param("salesmanId") Long salesmanId, Pageable pageable);

   Products findByname(String name);

}
