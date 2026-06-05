package br.com.fiap.api_agrovista.dto.mensagem;

import br.com.fiap.api_agrovista.model.enums.TipoMensagem;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.UUID;

public record MensagemResponse(
        UUID id,
        TipoMensagem tipo,
        String conteudo,
        String arquivoNome,
        LocalDateTime enviadoEm,
        Link linkConversa,
        Link linkMensagem
) {}
