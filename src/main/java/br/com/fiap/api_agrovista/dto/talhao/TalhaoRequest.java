package br.com.fiap.api_agrovista.dto.talhao;

import br.com.fiap.api_agrovista.model.enums.StatusTalhao;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record TalhaoRequest(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Cultura é obrigatória")
        String cultura,

        @NotNull(message = "Área é obrigatória")
        @Positive(message = "Área deve ser maior que zero")
        Double areaHa,

        @NotNull(message = "Status é obrigatório")
        @JsonProperty("status")
        StatusTalhao status,

        @NotNull(message = "Usuário é obrigatório")
        UUID idUsuario

) {}