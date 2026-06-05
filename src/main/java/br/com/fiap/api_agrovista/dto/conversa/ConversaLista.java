package br.com.fiap.api_agrovista.dto.conversa;

import br.com.fiap.api_agrovista.model.enums.CanalConversa;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;

public record ConversaLista(
        CanalConversa canal,
        LocalDateTime createdAt,
        Link linkUsuario,
        Link linkConversa
) {}