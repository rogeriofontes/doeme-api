package br.com.doeme.ong.model.service.impl;

import br.com.doeme.ong.model.entity.Ong;
import br.com.doeme.ong.model.repositories.OngRepository;
import br.com.doeme.ong.model.service.OngService;
import br.com.doeme.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OngServiceImpl implements OngService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OngRepository ongRepository;

    @Override
    public Ong save(Ong ong) {
        return ongRepository.save(ong);
    }
    
    @Override
    public Ong update(Long id, Ong ong) {
        Optional<Ong> ongById = findById(id);
        if (ongById.isPresent()) {
            var pessoaUpdate = ongById.get();
            pessoaUpdate.update(id, ong);
            return ongRepository.save(pessoaUpdate);
        }

        return new Ong();
    }

    @Override
    public List<Ong> list() {
        return ongRepository.findAll();
    }

    @Override
    public Optional<Ong> findById(Long id) {
        return ongRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        ongRepository.deleteById(id);
    }

    @Override
    public Optional<Ong> findByUserId(Long userId) {
        return ongRepository.findByUserId(userId);
    }


}
