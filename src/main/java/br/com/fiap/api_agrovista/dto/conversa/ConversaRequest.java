package br.com.fiap.api_agrovista.dto.conversa;

import br.com.fiap.api_agrovista.model.enums.CanalConversa;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ConversaRequest(

        @NotNull(message = "Usuário é obrigatório")
        UUID idUsuario,

        @NotNull(message = "Canal é obrigatório")
        CanalConversa canal

) {}