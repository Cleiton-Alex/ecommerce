package projeto.ecommerce.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.ecommerce.entities.Salesman;
import projeto.ecommerce.repositories.SalesmanRepository;
import projeto.ecommerce.services.SalesmanService;

import java.util.Optional;

@Service
public class SalesmanServiceImpl implements SalesmanService {
    private static final Logger log = LoggerFactory.getLogger(SalesmanServiceImpl.class);

    @Autowired
    SalesmanRepository salesmanRepository;




    @Override
    public Salesman pesistir(Salesman salesman) {
        log.info("Persistindo salesman: {}", salesman);
        return this.salesmanRepository.save(salesman);
    }

    @Override
    public Optional<Salesman> buscarPorCpf(String cpf) {
        log.info("Buscando Salesman por Cpf : {}", cpf );
       return Optional.ofNullable(this.salesmanRepository.findByCpf(cpf));
    }

    @Override
    public Optional<Salesman> buscarPorId(Long id) {
        log.info("Buscando Salesman por ID: {}", id);
        return this.salesmanRepository.findById(id);
    }

    @Override
    public void remover(Long id) {
        log.info("Removendo Lançamento por ID {}", id);
        this.salesmanRepository.deleteById(id);
    }

}
