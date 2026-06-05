package br.com.fiap.api_agrovista.dto.historicoAlerta;

import br.com.fiap.api_agrovista.model.enums.ResultadoAlerta;
import org.springframework.hateoas.Link;

import java.time.LocalDate;
import java.util.UUID;

public record HistoricoAlertaResponse(
        UUID id,
        ResultadoAlerta resultado,
        String descricao,
        LocalDate dataEvento,
        Link linkAlerta,
        Link linkTalhao,
        Link linkHistorico
) {}