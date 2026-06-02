package br.com.fiap.api_agrovista.repository;

import br.com.fiap.api_agrovista.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
   // findByEmail(String email);
}
