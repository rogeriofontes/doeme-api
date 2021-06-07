package br.com.doeme.pessoa.model.service.impl;

import br.com.doeme.exceptions.ResourceFoundException;
import br.com.doeme.pessoa.model.entity.Pessoa;
import br.com.doeme.pessoa.model.repositories.PessoaRepository;
import br.com.doeme.pessoa.model.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    @Cacheable(value = "pessoasCache")
    public Optional<Pessoa> findByName(String name) {
        return pessoaRepository.findByName(name);
    }

    @Override
    @CacheEvict(value = "pessoasCache", allEntries = true)
    public Pessoa save(Pessoa pessoa) throws ResourceFoundException {
        Optional<Pessoa> pessoaById = findByName(pessoa.getName());
        if (pessoaById.isPresent()) {
            throw new ResourceFoundException("Esse Recurso Já exite na nossa base");
        }

        return pessoaRepository.save(pessoa);
    }

    @Override
    @CacheEvict(value = "pessoasCache", allEntries = true)
    public Pessoa update(Long id, Pessoa pessoa) {
        Optional<Pessoa> pessoaById = findById(id);
        if (pessoaById.isPresent()) {
            var pessoaUpdate = pessoaById.get();
            pessoaUpdate.update(id, pessoa);
            return pessoaRepository.save(pessoaUpdate);
        }

        return new Pessoa();
    }

    @Override
    @Cacheable(value = "pessoasCache")
    public List<Pessoa> list() {
        return pessoaRepository.findAll();
    }

    @Override
    @Cacheable(value = "pessoasCache")
    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    @Override
    @CacheEvict(value = "pessoasCache", allEntries = true)
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }
}
