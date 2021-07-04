package br.com.doeme.ngo.model.service.impl;

import br.com.doeme.ngo.model.entity.Ngo;
import br.com.doeme.ngo.model.repositories.NgoRepository;
import br.com.doeme.ngo.model.service.NgoService;
import br.com.doeme.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NgoServiceImpl implements NgoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NgoRepository ngoRepository;

    @Override
    public Ngo save(Ngo ngo) {
        return ngoRepository.save(ngo);
    }
    
    @Override
    public Ngo update(Long id, Ngo ngo) {
        Optional<Ngo> ongById = findById(id);
        if (ongById.isPresent()) {
            var pessoaUpdate = ongById.get();
            pessoaUpdate.update(id, ngo);
            return ngoRepository.save(pessoaUpdate);
        }

        return new Ngo();
    }

    @Override
    public List<Ngo> list() {
        return ngoRepository.findAll();
    }

    @Override
    public Optional<Ngo> findById(Long id) {
        return ngoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        ngoRepository.deleteById(id);
    }

    @Override
    public Optional<Ngo> findByUserId(Long userId) {
        return ngoRepository.findByUserId(userId);
    }


}
