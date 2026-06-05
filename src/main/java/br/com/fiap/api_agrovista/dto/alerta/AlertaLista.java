package br.com.fiap.api_agrovista.dto.alerta;

import br.com.fiap.api_agrovista.model.enums.NivelAlerta;
import br.com.fiap.api_agrovista.model.enums.TipoAlerta;
import org.springframework.hateoas.Link;

public record AlertaLista(
        TipoAlerta tipo,
        NivelAlerta nivel,
        Boolean ativo,
        Link linkTalhao,
        Link linkAlerta
) {}