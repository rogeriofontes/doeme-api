package br.com.doeme.ong.model.repositories;

import br.com.doeme.ong.model.entity.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long> {
    Optional<Ong> findByUserId(Long userId);
}
