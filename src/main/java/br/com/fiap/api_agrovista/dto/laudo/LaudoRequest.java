package br.com.fiap.api_agrovista.dto.laudo;

import br.com.fiap.api_agrovista.model.enums.StatusLaudo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record LaudoRequest(

        @NotNull(message = "Talhão é obrigatório")
        UUID idTalhao,

        UUID idAlerta,

        @NotBlank(message = "Evento é obrigatório")
        String evento,

        @NotNull(message = "Status é obrigatório")
        StatusLaudo status,

        @Positive(message = "Tamanho deve ser maior que zero")
        BigDecimal tamanhoMb

) {}