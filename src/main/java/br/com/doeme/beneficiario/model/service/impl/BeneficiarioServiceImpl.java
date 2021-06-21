package br.com.doeme.beneficiario.model.service.impl;

import br.com.doeme.beneficiario.model.entity.Beneficiario;
import br.com.doeme.beneficiario.model.repositories.BeneficiarioRepository;
import br.com.doeme.beneficiario.model.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioServiceImpl implements BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Override
    public Optional<Beneficiario> findByEmail(String email) {
        return beneficiarioRepository.findByEmail(email);
    }

    @Override
    public Beneficiario save(Beneficiario e) throws Exception {
        return beneficiarioRepository.save(e);
    }

    @Override
    public Beneficiario update(Long aLong, Beneficiario e) {
        Optional<Beneficiario> opt      = beneficiarioRepository.findById(aLong);
        if(opt.isPresent()) {
            var toUpdate                = opt.get();
            toUpdate.update(aLong, e);
            return beneficiarioRepository.save(toUpdate);
        }
        return new Beneficiario();
    }

    @Override
    public List<Beneficiario> list() {
        return beneficiarioRepository.findAll();
    }

    @Override
    public Optional<Beneficiario> findById(Long aLong) {
        return beneficiarioRepository.findById(aLong);
    }

    @Override
    public void delete(Long aLong) {
        beneficiarioRepository.deleteById(aLong);
    }
}
