package br.com.fiap.api_agrovista.repository;

import br.com.fiap.api_agrovista.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {
}
