package br.com.fiap.api_agrovista.repository;

import br.com.fiap.api_agrovista.model.Talhao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TalhaoRepository extends JpaRepository<Talhao, UUID> {
}
