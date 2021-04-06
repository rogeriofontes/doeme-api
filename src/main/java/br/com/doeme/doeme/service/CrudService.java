package br.com.doeme.doeme.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID extends Serializable> {
    T save(T e) throws Exception;
    T update(ID id, T e);
    List<T> list();
    Optional<T> findById(ID id);
    void delete(ID id);
}
