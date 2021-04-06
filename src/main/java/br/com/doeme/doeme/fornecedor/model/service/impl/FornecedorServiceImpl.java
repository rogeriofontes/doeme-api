package br.com.doeme.doeme.fornecedor.model.service.impl;

import br.com.doeme.doeme.fornecedor.model.entity.Fornecedor;
import br.com.doeme.doeme.fornecedor.model.repositories.FornecedorRepository;
import br.com.doeme.doeme.fornecedor.model.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public Optional<Fornecedor> findByName(String name) {
        return fornecedorRepository.findByName(name);
    }

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @Override
    public Fornecedor update(Long id, Fornecedor fornecedor) {
        Optional<Fornecedor> pessoaById = findById(id);
        if (pessoaById.isPresent()) {
            var pessoaUpdate = pessoaById.get();
            pessoaUpdate.update(id, fornecedor);
            return fornecedorRepository.save(pessoaUpdate);
        }

        return new Fornecedor();
    }

    @Override
    public List<Fornecedor> list() {
        return fornecedorRepository.findAll();
    }

    @Override
    public Optional<Fornecedor> findById(Long id) {
        return fornecedorRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
