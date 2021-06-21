package br.com.doeme.doador.model.service.impl;

import br.com.doeme.doador.model.entity.Doador;
import br.com.doeme.doador.model.repositories.DoadorRepository;
import br.com.doeme.doador.model.service.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoadorServiceImpl implements DoadorService {

    @Autowired
    private DoadorRepository doadorRepository;

    @Override
    public Optional<Doador> findByEmail(String email) {
        return doadorRepository.findByEmail(email);
    }

    @Override
    public Doador save(Doador e) throws Exception {
        return doadorRepository.save(e);
    }

    @Override
    public Doador update(Long aLong, Doador e) {
        Optional<Doador> opt            = doadorRepository.findById(aLong);
        if(opt.isPresent()) {
            var toUpdate        = opt.get();
            toUpdate.update(aLong, e);
            return doadorRepository.save(toUpdate);
        }
        return new Doador();
    }

    @Override
    public List<Doador> list() {
        return doadorRepository.findAll();
    }

    @Override
    public Optional<Doador> findById(Long aLong) {
        return doadorRepository.findById(aLong);
    }

    @Override
    public void delete(Long aLong) {
        doadorRepository.deleteById(aLong);
    }
}
