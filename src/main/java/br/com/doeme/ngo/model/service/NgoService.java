package br.com.doeme.ngo.model.service;

import br.com.doeme.ngo.model.entity.Ngo;
import br.com.doeme.service.CrudService;

import java.util.Optional;

public interface NgoService extends CrudService<Ngo, Long> {
    Optional<Ngo> findByUserId(Long userId);
}
