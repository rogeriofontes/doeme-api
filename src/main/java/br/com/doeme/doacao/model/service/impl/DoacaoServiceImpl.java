package br.com.doeme.doacao.model.service.impl;

import br.com.doeme.doacao.model.entity.Doacao;
import br.com.doeme.doacao.model.repositories.DoacaoRepository;
import br.com.doeme.doacao.model.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoacaoServiceImpl implements DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Override
    public Doacao save(Doacao e) throws Exception {
        return doacaoRepository.save(e);
    }

    @Override
    public Doacao update(Long aLong, Doacao e) {
        return new Doacao();
    }

    @Override
    public List<Doacao> list() {
        return doacaoRepository.findAll();
    }

    @Override
    public Optional<Doacao> findById(Long aLong) {
        return doacaoRepository.findById(aLong);
    }

    @Override
    public void delete(Long aLong) {
        doacaoRepository.deleteById(aLong);
    }
}
