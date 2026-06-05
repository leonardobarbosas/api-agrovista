package br.com.fiap.api_agrovista.dto.conversa;

import br.com.fiap.api_agrovista.model.enums.CanalConversa;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConversaResponse(
        UUID id,
        CanalConversa canal,
        LocalDateTime createdAt,
        Link linkUsuario,
        Link linkConversa
) {}