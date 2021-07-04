package br.com.doeme.necessity.model.repositories;

import br.com.doeme.necessity.model.entity.Necessity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NecessityRepository extends JpaRepository<Necessity, Long> {
    Optional<Necessity> findByBeneficiaryId(Long userId);
}
