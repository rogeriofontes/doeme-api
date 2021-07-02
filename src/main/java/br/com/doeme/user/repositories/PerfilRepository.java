package br.com.doeme.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<br.com.doeme.user.entiry.Profile, Long> {
    Optional<br.com.doeme.user.entiry.Profile> findByRole(String role);
}
