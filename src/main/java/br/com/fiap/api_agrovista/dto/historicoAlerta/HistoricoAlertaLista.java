package br.com.fiap.api_agrovista.dto.historicoAlerta;

import br.com.fiap.api_agrovista.model.enums.ResultadoAlerta;
import org.springframework.hateoas.Link;

import java.time.LocalDate;

public record HistoricoAlertaLista(
        ResultadoAlerta resultado,
        LocalDate dataEvento,
        Link linkAlerta,
        Link linkHistorico
) {}