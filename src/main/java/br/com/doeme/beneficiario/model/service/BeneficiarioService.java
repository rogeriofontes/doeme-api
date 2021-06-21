package br.com.doeme.beneficiario.model.service;

import br.com.doeme.beneficiario.model.entity.Beneficiario;
import br.com.doeme.service.CrudService;

import java.util.Optional;

public interface BeneficiarioService extends CrudService<Beneficiario, Long> {
    Optional<Beneficiario> findByEmail(String email);
}
