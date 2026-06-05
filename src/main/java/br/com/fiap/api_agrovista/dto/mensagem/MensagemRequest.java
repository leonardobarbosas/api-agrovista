package br.com.fiap.api_agrovista.dto.mensagem;

import br.com.fiap.api_agrovista.model.enums.TipoMensagem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record MensagemRequest(

        @NotNull(message = "Conversa é obrigatória")
        UUID idConversa,

        @NotNull(message = "Tipo é obrigatório")
        TipoMensagem tipo,

        @NotBlank(message = "Conteúdo é obrigatório")
        String conteudo,

        String arquivoNome

) {}