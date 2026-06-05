package br.com.fiap.api_agrovista.repository;

import br.com.fiap.api_agrovista.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlertaRepository extends JpaRepository<Alerta, UUID> {
}
