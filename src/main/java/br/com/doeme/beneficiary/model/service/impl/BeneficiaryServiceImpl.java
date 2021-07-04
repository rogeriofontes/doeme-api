package br.com.doeme.beneficiary.model.service.impl;

import br.com.doeme.beneficiary.model.entity.Beneficiary;
import br.com.doeme.beneficiary.model.repositories.BeneficiaryRepository;
import br.com.doeme.beneficiary.model.service.BeneficiaryService;
import br.com.doeme.user.entiry.User;
import br.com.doeme.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Override
    public Beneficiary save(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }

    @Override
    public Beneficiary update(Long id, Beneficiary beneficiary) {
        Optional<Beneficiary> beneficiaryById = findById(id);
        if (beneficiaryById.isPresent()) {
            var pessoaUpdate = beneficiaryById.get();
            pessoaUpdate.update(id, beneficiary);
            return beneficiaryRepository.save(pessoaUpdate);
        }

        return new Beneficiary();
    }

    @Override
    public List<Beneficiary> list() {
        return beneficiaryRepository.findAll();
    }

    @Override
    public Optional<Beneficiary> findById(Long id) {
        return beneficiaryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        beneficiaryRepository.deleteById(id);
    }

    @Override
    public Optional<Beneficiary> findByUserId(Long userId) {
        return beneficiaryRepository.findByUserId(userId);
    }


}
