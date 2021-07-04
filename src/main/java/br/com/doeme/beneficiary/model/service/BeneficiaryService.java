package br.com.doeme.beneficiary.model.service;

import br.com.doeme.beneficiary.model.entity.Beneficiary;
import br.com.doeme.service.CrudService;

import java.util.Optional;

public interface BeneficiaryService extends CrudService<Beneficiary, Long> {
    Optional<Beneficiary> findByUserId(Long userId);
}
