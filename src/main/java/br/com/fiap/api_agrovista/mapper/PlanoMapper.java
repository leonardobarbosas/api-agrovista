package br.com.fiap.api_agrovista.mapper;

import br.com.fiap.api_agrovista.controller.PlanoController;
import br.com.fiap.api_agrovista.dto.plano.PlanoLista;
import br.com.fiap.api_agrovista.dto.plano.PlanoResponse;
import br.com.fiap.api_agrovista.model.Plano;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlanoMapper {

    public PlanoResponse planoToResponse(Plano plano) {
        Link linkPlano = linkTo(methodOn(PlanoController.class).findById(plano.getId())).withRel("Detalhes do plano");
        return new PlanoResponse(plano.getId(), plano.getNome(), plano.getPrecoMensal(), plano.getSubtexto(), linkPlano);
    }

    public PlanoLista planoToResponseLista(Plano plano) {
        Link linkPlano = linkTo(methodOn(PlanoController.class).findById(plano.getId())).withRel("Detalhes do plano");
        return new PlanoLista(plano.getNome(), plano.getPrecoMensal(), plano.getSubtexto(), linkPlano);
    }
}
