package br.com.fiap.api_agrovista.repository;

import br.com.fiap.api_agrovista.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanoRepository extends JpaRepository<Plano, UUID> {
}
