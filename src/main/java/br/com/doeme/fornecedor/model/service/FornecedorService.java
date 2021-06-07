package br.com.doeme.fornecedor.model.service;

import br.com.doeme.fornecedor.model.entity.Fornecedor;
import br.com.doeme.service.CrudService;

import java.util.Optional;

public interface FornecedorService extends CrudService<Fornecedor, Long> {
    Optional<Fornecedor> findByName(String name);
}
