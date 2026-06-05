package br.com.fiap.api_agrovista.dto.mensagem;

import br.com.fiap.api_agrovista.model.enums.TipoMensagem;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;

public record MensagemLista(
        TipoMensagem tipo,
        String conteudo,
        LocalDateTime enviadoEm,
        Link linkConversa,
        Link linkMensagem
) {}