package projeto.ecommerce.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.ecommerce.entities.Salesman;
import projeto.ecommerce.repositories.SalesmanRepository;
import projeto.ecommerce.services.SalesmanService;

import java.util.List;
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
    public Optional<Salesman> buscarPorStatus(Integer status) {
        log.info("Buscando Salesman por Status: {}", status);
        return Optional.ofNullable(this.salesmanRepository.findByStatus(status));
    }

    @Override
    public void remover(Long id) {
        log.info("Removendo Lançamento por ID {}", id);
        this.salesmanRepository.deleteById(id);
    }

    @Override
    public List<Salesman> buscarTodos() {
        log.info("Buscar Todos Salesman ID {}");
        return this.salesmanRepository.findAll();
    }

    @Override
    public Optional<Salesman> buscarSalesmanUser(Long id) {
        log.info("Buscar Salesman pelo ID User {}");
        return Optional.ofNullable(this.salesmanRepository.findByUser_Id(id));
    }

}
