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
    public Page<Products> buscarPorCategoiresOrProducts(Long categoriesId, String names, PageRequest pageRequest) {
        log.info("Buscando Por categorias ID e name produtos: {}", categoriesId, names);
        return this.productsRepository.findByCategories(categoriesId, pageRequest);
    }

    @Override
    public Page<Products> buscarPorSalesmanOrProducts(Long salesmanId, String names, PageRequest pageRequest) {
        log.info("Buscando Por salesmans ID e name produtos: {}", salesmanId, names);
        return this.productsRepository.findBySalesman(salesmanId, pageRequest);
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


}
