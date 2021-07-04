package br.com.doeme.ngo.model.repositories;

import br.com.doeme.ngo.model.entity.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NgoRepository extends JpaRepository<Ngo, Long> {
    Optional<Ngo> findByUserId(Long userId);
}
