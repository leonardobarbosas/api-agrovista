package br.com.fiap.api_agrovista.mapper;

import br.com.fiap.api_agrovista.controller.ConversaController;
import br.com.fiap.api_agrovista.controller.UsuarioController;
import br.com.fiap.api_agrovista.dto.conversa.ConversaLista;
import br.com.fiap.api_agrovista.dto.conversa.ConversaResponse;
import br.com.fiap.api_agrovista.model.Conversa;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ConversaMapper {

    public ConversaResponse conversaToResponse(Conversa conversa) {

        Link linkUsuario = linkTo(methodOn(UsuarioController.class)
                .findById(conversa.getUsuario().getId()))
                .withRel("Detalhes do usuário");

        Link linkConversa = linkTo(methodOn(ConversaController.class)
                .findById(conversa.getId()))
                .withRel("Detalhes da conversa");

        return new ConversaResponse(
                conversa.getId(),
                conversa.getCanal(),
                conversa.getCreatedAt(),
                linkUsuario,
                linkConversa
        );
    }

    public ConversaLista conversaToLista(Conversa conversa) {

        Link linkUsuario = linkTo(methodOn(UsuarioController.class)
                .findById(conversa.getUsuario().getId()))
                .withRel("Detalhes do usuário");

        Link linkConversa = linkTo(methodOn(ConversaController.class)
                .findById(conversa.getId()))
                .withRel("Detalhes da conversa");

        return new ConversaLista(
                conversa.getCanal(),
                conversa.getCreatedAt(),
                linkUsuario,
                linkConversa
        );
    }

}