package br.com.fiap.api_agrovista.dto.plano;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record PlanoRequest(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Preço mensal é obrigatório")
        @Digits(integer = 8, fraction = 2, message = "Preço inválido")
        BigDecimal precoMensal,

        @NotNull(message = "Destaque é obrigatório")
        boolean destaque,

        String subtexto
) {
}
