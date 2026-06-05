package br.com.fiap.api_agrovista.dto.alerta;

import br.com.fiap.api_agrovista.model.enums.NivelAlerta;
import br.com.fiap.api_agrovista.model.enums.TipoAlerta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AlertaRequest(

        @NotNull(message = "Talhão é obrigatório")
        UUID idTalhao,

        @NotNull(message = "Tipo é obrigatório")
        TipoAlerta tipo,

        @NotNull(message = "Nível é obrigatório")
        NivelAlerta nivel,

        @NotNull(message = "Ativo é obrigatório")
        Boolean ativo,

        @NotBlank(message = "Descrição é obrigatória")
        String descricao

) {}