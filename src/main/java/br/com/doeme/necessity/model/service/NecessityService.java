package br.com.doeme.necessity.model.service;

import br.com.doeme.necessity.model.entity.Necessity;
import br.com.doeme.service.CrudService;

import java.util.Optional;

public interface NecessityService extends CrudService<Necessity, Long> {
    Optional<Necessity> findByBeneficiaryId(Long userId);
}
