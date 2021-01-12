package projeto.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import projeto.ecommerce.entities.Products;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



@Transactional(readOnly = true)
@NamedQueries({
        @NamedQuery(name = "ProductsRepository.findByCategoriesOrNameProduct",

                query = "SELECT categ FROM Categories categ WHERE categ.id = :categoriesID"),

        @NamedQuery(name = "ProductsRepository.findByCategoriesOrNameProduct",
            query =  "SELECT  sales from Salesman sales where sales.id= :salesmanID")})

public interface ProductsRepository extends JpaRepository<Products, Long> {

   Page<Products> findByCategoriesIdAndNameProduct(@Param("categoriesID") Long categoriesId, String nameProducts, Pageable pageable);
   Page<Products> findBySalesmanIdAndAndNameProduct(@Param("salesmanID") Long salesmanId, String nameProducts, Pageable pageable);

   Products findByname(String name);

}
