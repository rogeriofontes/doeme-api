package br.com.doeme.doeme.fornecedor.model.service;

import br.com.doeme.doeme.fornecedor.model.entity.Fornecedor;
import br.com.doeme.doeme.service.CrudService;

import java.util.Optional;

public interface FornecedorService extends CrudService<Fornecedor, Long> {
    Optional<Fornecedor> findByName(String name);
}
