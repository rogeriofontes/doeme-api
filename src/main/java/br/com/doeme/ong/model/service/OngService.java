package br.com.doeme.ong.model.service;

import br.com.doeme.ong.model.entity.Ong;
import br.com.doeme.service.CrudService;

import java.util.Optional;

public interface OngService extends CrudService<Ong, Long> {
    Optional<Ong> findByUserId(Long userId);
}
