package br.com.fiap.api_agrovista.mapper;

import br.com.fiap.api_agrovista.controller.ConversaController;
import br.com.fiap.api_agrovista.controller.MensagemController;
import br.com.fiap.api_agrovista.dto.mensagem.MensagemLista;
import br.com.fiap.api_agrovista.dto.mensagem.MensagemResponse;
import br.com.fiap.api_agrovista.model.Mensagem;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MensagemMapper {

    public MensagemResponse mensagemToResponse(Mensagem mensagem) {

        Link linkConversa = linkTo(methodOn(ConversaController.class)
                .findById(mensagem.getConversa().getId()))
                .withRel("Detalhes da conversa");

        Link linkMensagem = linkTo(methodOn(MensagemController.class)
                .findById(mensagem.getId()))
                .withRel("Detalhes da mensagem");

        return new MensagemResponse(
                mensagem.getId(),
                mensagem.getTipo(),
                mensagem.getConteudo(),
                mensagem.getArquivoNome(),
                mensagem.getEnviadoEm(),
                linkConversa,
                linkMensagem
        );
    }

    public MensagemLista mensagemToLista(Mensagem mensagem) {

        Link linkConversa = linkTo(methodOn(ConversaController.class)
                .findById(mensagem.getConversa().getId()))
                .withRel("Detalhes da conversa");

        Link linkMensagem = linkTo(methodOn(MensagemController.class)
                .findById(mensagem.getId()))
                .withRel("Detalhes da mensagem");

        return new MensagemLista(
                mensagem.getTipo(),
                mensagem.getConteudo(),
                mensagem.getEnviadoEm(),
                linkConversa,
                linkMensagem
        );
    }
}