package br.com.fiap.api_agrovista.repository;

import br.com.fiap.api_agrovista.model.HistoricoAlerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoricoAlertaRepository extends JpaRepository<HistoricoAlerta, UUID> {
}
