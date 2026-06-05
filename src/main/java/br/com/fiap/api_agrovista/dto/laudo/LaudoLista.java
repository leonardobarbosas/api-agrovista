package br.com.fiap.api_agrovista.dto.laudo;

import br.com.fiap.api_agrovista.model.enums.StatusLaudo;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;

public record LaudoLista(
        String evento,
        StatusLaudo status,
        LocalDateTime geradoEm,
        Link linkTalhao,
        Link linkLaudo
) {}