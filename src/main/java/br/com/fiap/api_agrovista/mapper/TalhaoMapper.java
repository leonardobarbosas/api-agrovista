package br.com.fiap.api_agrovista.mapper;

import br.com.fiap.api_agrovista.controller.TalhaoController;
import br.com.fiap.api_agrovista.controller.UsuarioController;
import br.com.fiap.api_agrovista.dto.talhao.TalhaoLista;
import br.com.fiap.api_agrovista.dto.talhao.TalhaoResponse;
import br.com.fiap.api_agrovista.model.Talhao;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TalhaoMapper {

    public TalhaoResponse talhaoToResponse(Talhao talhao){
        Link linkTalhao = linkTo(methodOn(TalhaoController.class).findAll(0)).withRel("Lista de talhões");
        Link linkUsuario = linkTo(methodOn(UsuarioController.class).findById(talhao.getUsuario().getId())).withRel("Detalhes do usuário");
        return new TalhaoResponse(talhao.getId(), talhao.getNome(), talhao.getCultura(), talhao.getAreaHa(), talhao.getStatus(), linkUsuario, linkTalhao);
    }

    public TalhaoLista talhaoToResponseLista(Talhao talhao  ) {
        Link linkTalhao = linkTo(methodOn(TalhaoController.class).findById(talhao.getId())).withRel("Detalhes do talhão");
        Link linkUsuario = linkTo(methodOn(UsuarioController.class).findById(talhao.getUsuario().getId())).withRel("Detalhes do usuário");
        return new TalhaoLista(talhao.getNome(), talhao.getCultura(), talhao.getAreaHa(), talhao.getStatus(), linkUsuario, linkTalhao);
    }
}
