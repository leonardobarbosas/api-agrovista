package br.com.fiap.api_agrovista.dto.historicoAlerta;

import br.com.fiap.api_agrovista.model.enums.ResultadoAlerta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record HistoricoAlertaRequest(

        @NotNull(message = "Alerta é obrigatório")
        UUID idAlerta,

        @NotNull(message = "Talhão é obrigatório")
        UUID idTalhao,

        @NotNull(message = "Resultado é obrigatório")
        ResultadoAlerta resultado,

        @NotBlank(message = "Descrição é obrigatória")
        String descricao,

        @NotNull(message = "Data do evento é obrigatória")
        LocalDate dataEvento

) {}