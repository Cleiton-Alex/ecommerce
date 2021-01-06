package projeto.Ecommerce.services;

import projeto.Ecommerce.entities.Salesman;

import java.util.Optional;

public interface MidiasService {

    Salesman persiste(Salesman salesman);

    /**
     * Buscar e retorna todas as Midias ou midias que nao foram Deletadas
     *
     * @param deletadoMidias
     * @return Optional<Midias>
     */
    Optional<Salesman> buscarPorMidiasNaoDeletadas(String deletadoMidias);


    /**
     * Buscar e retorna uma Midia dado o id
     *
     * @param id
     * @return Optional<Midias>
     */
    Optional<Salesman> buscarPorMidiasID(Long id);


    /**
     * Removo uma midia dado o id
     *
     * @param id
     * */
    void remover(Long id);
}


