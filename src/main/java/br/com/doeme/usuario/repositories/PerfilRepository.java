package br.com.doeme.usuario.repositories;

import br.com.doeme.usuario.entiry.Perfil;
import br.com.doeme.usuario.entiry.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByRole(String role);
}
