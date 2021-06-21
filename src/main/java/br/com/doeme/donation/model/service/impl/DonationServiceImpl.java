package br.com.doeme.donation.model.service.impl;

import br.com.doeme.donation.model.entity.Donation;
import br.com.doeme.donation.model.repositories.DonationRepository;
import br.com.doeme.donation.model.service.DonationService;
import br.com.doeme.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Override
    public Donation save(Donation donation) {
        if (StringUtils.isEmpty(donation.getCode())) {
            donation.setCode(UUIDUtil.shortUUID());
        }
        return donationRepository.save(donation);
    }

    @Override
    public Donation update(Long id, Donation donation) {
        Optional<Donation> fornecedorById = findById(id);
        if (fornecedorById.isPresent()) {
            var pessoaUpdate = fornecedorById.get();
            pessoaUpdate.update(id, donation);
            return donationRepository.save(pessoaUpdate);
        }

        return new Donation();
    }

    @Override
    public List<Donation> list() {
        return donationRepository.findAll();
    }

    @Override
    public Optional<Donation> findById(Long id) {
        return donationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        donationRepository.deleteById(id);
    }
}
