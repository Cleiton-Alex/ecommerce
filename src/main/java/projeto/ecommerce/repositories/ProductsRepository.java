package projeto.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import projeto.ecommerce.entities.Products;



public interface ProductsRepository extends JpaRepository<Products, Long> {

   Page<Products> findByCategoriesOrNameProduct(@Param("categoriesId") Long categoriesId, String nameProducts, Pageable pageable);
   Page<Products> findBySalesmanOrNameProduct(@Param("salesmanId") Long salesmanId, String nameProducts, Pageable pageable);

   Products findByNameProduct(String nameProduct);

}
