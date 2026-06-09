package br.com.fiap.api_agrovista.mapper;

import br.com.fiap.api_agrovista.controller.AlertaController;
import br.com.fiap.api_agrovista.controller.LaudoController;
import br.com.fiap.api_agrovista.controller.TalhaoController;
import br.com.fiap.api_agrovista.dto.laudo.LaudoLista;
import br.com.fiap.api_agrovista.dto.laudo.LaudoResponse;
import br.com.fiap.api_agrovista.model.Laudo;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LaudoMapper {

    public LaudoResponse laudoToResponse(Laudo laudo) {

        Link linkTalhao = linkTo(methodOn(TalhaoController.class)
                .findById(laudo.getTalhao().getId()))
                .withRel("Detalhes do talhão");

        Link linkAlerta = laudo.getAlerta() != null
                ? linkTo(methodOn(AlertaController.class)
                .findById(laudo.getAlerta().getId()))
                .withRel("Detalhes do alerta")
                : null;

        Link linkLaudo = linkTo(methodOn(LaudoController.class)
                .findById(laudo.getId()))
                .withRel("Detalhes do laudo");

        return new LaudoResponse(
                laudo.getId(),
                laudo.getEvento(),
                laudo.getStatus(),
                laudo.getTamanhoMb(),
                laudo.getGeradoEm(),
                linkTalhao,
                linkAlerta,
                linkLaudo
        );
    }

    public LaudoLista laudoToLista(Laudo laudo) {

        Link linkTalhao = linkTo(methodOn(TalhaoController.class)
                .findById(laudo.getTalhao().getId()))
                .withRel("Detalhes do talhão");

        Link linkLaudo = linkTo(methodOn(LaudoController.class)
                .findById(laudo.getId()))
                .withRel("Detalhes do laudo");

        return new LaudoLista(
                laudo.getEvento(),
                laudo.getStatus(),
                laudo.getGeradoEm(),
                linkTalhao,
                linkLaudo
        );
    }

}