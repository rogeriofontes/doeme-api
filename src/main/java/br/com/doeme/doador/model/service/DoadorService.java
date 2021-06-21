package br.com.doeme.doador.model.service;

import br.com.doeme.doador.model.entity.Doador;
import br.com.doeme.service.CrudService;

import java.util.Optional;

public interface DoadorService extends CrudService<Doador, Long> {
    Optional<Doador> findByEmail(String email);
}
