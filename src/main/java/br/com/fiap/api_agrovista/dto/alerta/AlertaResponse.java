package br.com.fiap.api_agrovista.dto.alerta;

import br.com.fiap.api_agrovista.model.enums.NivelAlerta;
import br.com.fiap.api_agrovista.model.enums.TipoAlerta;
import org.springframework.hateoas.Link;
import java.util.UUID;

public record AlertaResponse(
        UUID id,
        TipoAlerta tipo,
        NivelAlerta nivel,
        Boolean ativo,
        String descricao,
        Link linkTalhao,
        Link linkAlerta
) {}