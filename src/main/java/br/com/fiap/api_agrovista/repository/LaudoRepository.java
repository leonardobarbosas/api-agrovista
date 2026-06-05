package br.com.fiap.api_agrovista.repository;

import br.com.fiap.api_agrovista.model.Laudo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LaudoRepository extends JpaRepository<Laudo, UUID> {
}
