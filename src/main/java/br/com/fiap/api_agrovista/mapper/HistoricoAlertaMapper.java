package br.com.fiap.api_agrovista.mapper;

import br.com.fiap.api_agrovista.controller.AlertaController;
import br.com.fiap.api_agrovista.controller.HistoricoAlertaController;
import br.com.fiap.api_agrovista.controller.TalhaoController;
import br.com.fiap.api_agrovista.dto.historicoAlerta.HistoricoAlertaLista;
import br.com.fiap.api_agrovista.dto.historicoAlerta.HistoricoAlertaResponse;
import br.com.fiap.api_agrovista.model.HistoricoAlerta;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HistoricoAlertaMapper {

    public HistoricoAlertaResponse historicoToResponse(HistoricoAlerta historico) {

        Link linkAlerta = linkTo(methodOn(AlertaController.class)
                .findById(historico.getAlerta().getId()))
                .withRel("Detalhes do alerta");

        Link linkTalhao = linkTo(methodOn(TalhaoController.class)
                .findById(historico.getTalhao().getId()))
                .withRel("Detalhes do talhão");

        Link linkHistorico = linkTo(methodOn(HistoricoAlertaController.class)
                .findById(historico.getId()))
                .withRel("Detalhes do histórico");

        return new HistoricoAlertaResponse(
                historico.getId(),
                historico.getResultado(),
                historico.getDescricao(),
                historico.getDataEvento(),
                linkAlerta,
                linkTalhao,
                linkHistorico
        );
    }

    public HistoricoAlertaLista historicoToLista(HistoricoAlerta historico) {

        Link linkAlerta = linkTo(methodOn(AlertaController.class)
                .findById(historico.getAlerta().getId()))
                .withRel("Detalhes do alerta");

        Link linkHistorico = linkTo(methodOn(HistoricoAlertaController.class)
                .findById(historico.getId()))
                .withRel("Detalhes do histórico");

        return new HistoricoAlertaLista(
                historico.getResultado(),
                historico.getDataEvento(),
                linkAlerta,
                linkHistorico
        );
    }
}