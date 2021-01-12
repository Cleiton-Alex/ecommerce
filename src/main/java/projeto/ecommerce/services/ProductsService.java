package projeto.ecommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import projeto.ecommerce.entities.Products;

import java.util.List;
import java.util.Optional;

public interface ProductsService {

    /**
     * @param products
     * @return Salesman
     * */
    Products pesistir(Products products);


    Page<Products> buscarPorCategoiresOrProducts(Long categoriesId, String namepProducts, PageRequest pageRequest);


    Page<Products> buscarPorSalesmanOrProducts(Long salesmanId, String nameProducts, PageRequest pageRequest);

    Optional<Products> buscarPorNomeProduct(String nomeProduct);

    Optional<Products> buscarPorId(Long id);

    void remover(Long id);

    List<Products> buscarTodos();
}
