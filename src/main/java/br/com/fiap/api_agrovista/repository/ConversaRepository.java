package br.com.fiap.api_agrovista.repository;

import br.com.fiap.api_agrovista.model.Conversa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConversaRepository extends JpaRepository<Conversa, UUID> {
}
