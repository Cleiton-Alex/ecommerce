package projeto.Ecommerce.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.Ecommerce.repositories.MidiasRepository;
import projeto.Ecommerce.entities.Salesman;
import projeto.Ecommerce.services.MidiasService;


import java.util.Optional;

@Service
public class MidiasServiceImpl implements MidiasService {
    private  static final Logger log = LoggerFactory.getLogger(MidiasServiceImpl.class);

    @Autowired
    private MidiasRepository midiasRepository;

    @Override
    public Salesman persiste(Salesman salesman) {
        log.info("Persistindo Lancamento por ID {}", salesman);
        return this.midiasRepository.save(salesman);
    }

    @Override
    public Optional<Salesman> buscarPorMidiasNaoDeletadas(String deletadoMidias) {
        log.info("Buscar Todas as midias ou midias que nao foram deletadas", deletadoMidias);
        return Optional.empty();
    }

    @Override
    public Optional<Salesman> buscarPorMidiasID(Long id) {
        log.info("Buscar midia por ID {}", id);

        return this.midiasRepository.findById(id);
    }

    @Override
    public void remover(Long id) {
        log.info("Removendo midias por ID {}", id);
        this.midiasRepository.deleteById(id);
    }


}
