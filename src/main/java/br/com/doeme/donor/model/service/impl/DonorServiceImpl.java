package br.com.doeme.donor.model.service.impl;

import br.com.doeme.donor.model.entity.Donor;
import br.com.doeme.donor.model.repositories.DonorRepository;
import br.com.doeme.donor.model.service.DonorService;
import br.com.doeme.user.entiry.UserType;
import br.com.doeme.user.entiry.User;
import br.com.doeme.user.repositories.UserRepository;
import br.com.doeme.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonorServiceImpl implements DonorService {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Donor save(Donor donor) {
        return donorRepository.save(donor);
    }

    @Override
    public Donor update(Long id, Donor donor) {
        Optional<Donor> fornecedorById = findById(id);
        if (fornecedorById.isPresent()) {
            var pessoaUpdate = fornecedorById.get();
            pessoaUpdate.update(id, donor);
            return donorRepository.save(pessoaUpdate);
        }

        return new Donor();
    }

    @Override
    public List<Donor> list() {
        return donorRepository.findAll();
    }

    @Override
    public Optional<Donor> findById(Long id) {
        return donorRepository.findById(id);
    }

    @Override
    public Optional<Donor> finByUserId(Long id) {
        return donorRepository.findByUserId(id);
    }

    @Override
    public void delete(Long id) {
        donorRepository.deleteById(id);
    }
}
