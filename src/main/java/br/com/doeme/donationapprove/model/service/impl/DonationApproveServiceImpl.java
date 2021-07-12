package br.com.doeme.donationapprove.model.service.impl;

import br.com.doeme.donationapprove.model.entity.DonationApprove;
import br.com.doeme.donationapprove.model.repositories.DonationApproveRepository;
import br.com.doeme.donationapprove.model.service.DonationApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationApproveServiceImpl implements DonationApproveService {

    @Autowired
    private DonationApproveRepository donationApproveRepository;

    @Override
    public DonationApprove save(DonationApprove donationApprove) {
        return donationApproveRepository.save(donationApprove);
    }

    @Override
    public DonationApprove update(Long id, DonationApprove donationApprove) {
        Optional<DonationApprove> fornecedorById = findById(id);
        if (fornecedorById.isPresent()) {
            var pessoaUpdate = fornecedorById.get();
            pessoaUpdate.update(id, donationApprove);
            return donationApproveRepository.save(pessoaUpdate);
        }

        return new DonationApprove();
    }

    @Override
    public List<DonationApprove> list() {
        return donationApproveRepository.findAll();
    }

    @Override
    public Optional<DonationApprove> findById(Long id) {
        return donationApproveRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        donationApproveRepository.deleteById(id);
    }
}
