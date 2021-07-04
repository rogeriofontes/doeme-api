package br.com.doeme.grantee.model.service;

import br.com.doeme.grantee.model.entity.Grantee;
import br.com.doeme.service.CrudService;

import java.util.Optional;

public interface GranteeService extends CrudService<Grantee, Long> {
    Optional<Grantee> findByBeneficiaryId(Long userId);
}
