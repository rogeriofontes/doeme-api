package br.com.doeme.grantee.model.repositories;

import br.com.doeme.grantee.model.entity.Grantee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GranteeRepository extends JpaRepository<Grantee, Long> {
    Optional<Grantee> findByUserId(Long userId);
}
