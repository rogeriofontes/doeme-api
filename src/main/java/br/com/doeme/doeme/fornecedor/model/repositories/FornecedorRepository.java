package br.com.doeme.doeme.fornecedor.model.repositories;

import br.com.doeme.doeme.fornecedor.model.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Optional<Fornecedor> findByName(String name);
}
