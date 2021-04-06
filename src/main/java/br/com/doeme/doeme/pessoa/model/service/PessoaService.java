package br.com.doeme.doeme.pessoa.model.service;

import br.com.doeme.doeme.exceptions.ResourceFoundException;
import br.com.doeme.doeme.pessoa.model.entity.Pessoa;
import br.com.doeme.doeme.service.CrudService;

import java.util.Optional;

public interface PessoaService extends CrudService<Pessoa, Long> {
    Optional<Pessoa> findByName(String name);

    @Override
    Pessoa save(Pessoa pessoa) throws ResourceFoundException;
}
