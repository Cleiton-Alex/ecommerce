package projeto.ecommerce.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import projeto.ecommerce.entities.Products;
import projeto.ecommerce.repositories.ProductsRepository;
import projeto.ecommerce.services.ProductsService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {
    private static final Logger log = LoggerFactory.getLogger(ProductsServiceImpl.class);

    @Autowired
    ProductsRepository productsRepository;

    @Override
    public Products pesistir(Products products) {
        log.info("Persistindo empresa: {}", products);
        return this.productsRepository.save(products);
    }

    @Override
    public Page<Products> buscarPorCategoiresOrProducts(Long categoriesId, String nameProducts, PageRequest pageRequest) {
        log.info("Buscando Por categorias ID e name produtos: {}", categoriesId);
        return this.productsRepository.findByCategoriesIdAndNameProduct(categoriesId, nameProducts, pageRequest);
    }

    @Override
    public Page<Products> buscarPorSalesmanOrProducts(Long salesmanId, String nameProducts, PageRequest pageRequest) {
        log.info("Buscando Por salesmans ID e name produtos: {}", salesmanId, nameProducts);
        return this.productsRepository.findBySalesmanIdAndAndNameProduct(salesmanId,nameProducts, pageRequest);
    }

    @Override
    public Optional<Products> buscarPorNomeProduct(String nomeProduct) {
        log.info("Buscando Por nome do Product ID {}" , nomeProduct);
        return Optional.ofNullable(this.productsRepository.findByname(nomeProduct));
    }

    @Override
    public Optional<Products> buscarPorId(Long id) {
        log.info("Buscando Products Por ID {}", id);
        return this.productsRepository.findById(id);
    }

    @Override
    public void remover(Long id) {
        log.info("Removendo Lançamento por ID {}", id);
        this.productsRepository.deleteById(id);
    }

    @Override
    public List<Products> buscarTodos() {
        return this.productsRepository.findAll();
    }

}
