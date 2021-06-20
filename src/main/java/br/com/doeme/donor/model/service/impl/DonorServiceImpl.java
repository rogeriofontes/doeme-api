package br.com.doeme.donor.model.service.impl;

import br.com.doeme.donor.model.entity.Donor;
import br.com.doeme.donor.model.repositories.DonorRepository;
import br.com.doeme.donor.model.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {

    @Autowired
    private DonorRepository donorRepository;

    @Override
    public Optional<Donor> findByName(String name) {
        return donorRepository.findByName(name);
    }

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
    public void delete(Long id) {
        donorRepository.deleteById(id);
    }
}
