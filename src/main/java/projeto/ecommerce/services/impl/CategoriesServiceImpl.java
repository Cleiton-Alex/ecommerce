package projeto.ecommerce.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.ecommerce.entities.Categories;
import projeto.ecommerce.repositories.CategoriesRepository;
import projeto.ecommerce.services.CategoriesService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    private static final Logger log = LoggerFactory.getLogger(CategoriesServiceImpl.class);

    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public Categories pesistir(Categories categories) {
        log.info("Persistindo empresa: {}", categories);
        return this.categoriesRepository.save(categories);
    }

    @Override
    public Optional<Categories> buscarPorId(Long id) {
        log.info("Buscar Categories Por ID");
        return this.categoriesRepository.findById(id);
    }

    public List<Categories> getAll() {
        log.info("Buscar Categories Por ID");
        return this.categoriesRepository.findAll();
    }

    @Override
    public Optional<Categories> buscarPorName(String name) {
        return Optional.empty();
    }



}
