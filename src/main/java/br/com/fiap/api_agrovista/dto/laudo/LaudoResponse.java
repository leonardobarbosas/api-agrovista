package br.com.fiap.api_agrovista.dto.laudo;

import br.com.fiap.api_agrovista.model.enums.StatusLaudo;
import org.springframework.hateoas.Link;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LaudoResponse(
        UUID id,
        String evento,
        StatusLaudo status,
        BigDecimal tamanhoMb,
        LocalDateTime geradoEm,
        Link linkTalhao,
        Link linkAlerta,
        Link linkLaudo
) {}