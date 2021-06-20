package br.com.doeme.donor.model.service;

import br.com.doeme.donor.model.entity.Donor;
import br.com.doeme.service.CrudService;

import java.util.Optional;

public interface DonorService extends CrudService<Donor, Long> {
    Optional<Donor> findByName(String name);
}
