package br.com.fiap.api_agrovista.mapper;

import br.com.fiap.api_agrovista.controller.AlertaController;
import br.com.fiap.api_agrovista.controller.TalhaoController;
import br.com.fiap.api_agrovista.dto.alerta.AlertaLista;
import br.com.fiap.api_agrovista.dto.alerta.AlertaResponse;
import br.com.fiap.api_agrovista.model.Alerta;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlertaMapper {

    public AlertaResponse alertaToResponse(Alerta alerta) {

        Link linkAlerta = linkTo(methodOn(AlertaController.class)
                .findById(alerta.getId()))
                .withRel("Detalhes do alerta");

        Link linkTalhao = linkTo(methodOn(TalhaoController.class)
                .findById(alerta.getTalhao().getId()))
                .withRel("Talhão");

        return new AlertaResponse(
                alerta.getId(),
                alerta.getTipo(),
                alerta.getNivel(),
                alerta.isAtivo(),
                alerta.getDescricao(),
                linkTalhao,
                linkAlerta
        );
    }

    public AlertaLista alertaToLista(Alerta alerta) {

        Link link = linkTo(methodOn(AlertaController.class)
                .findById(alerta.getId()))
                .withRel("Detalhes do alerta");

        Link linkTalhao = linkTo(methodOn(TalhaoController.class)
                .findById(alerta.getTalhao().getId()))
                .withRel("Talhão");

        return new AlertaLista(
                alerta.getTipo(),
                alerta.getNivel(),
                alerta.isAtivo(),
                linkTalhao,
                link
        );
    }

}