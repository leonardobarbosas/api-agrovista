package br.com.fiap.api_agrovista.mapper;

import br.com.fiap.api_agrovista.controller.PlanoController;
import br.com.fiap.api_agrovista.controller.UsuarioController;
import br.com.fiap.api_agrovista.dto.usuario.UsuarioLista;
import br.com.fiap.api_agrovista.dto.usuario.UsuarioResponse;
import br.com.fiap.api_agrovista.model.Usuario;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioMapper {

    public UsuarioResponse usuarioToResponse(Usuario usuario) {
        Link linkPlano = usuario.getPlano() != null ?
                linkTo(methodOn(PlanoController.class).findById(usuario.getPlano().getId())).withSelfRel() : null;
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getTelefone(), usuario.getEmail(), linkPlano);
    }

    public UsuarioLista usuarioToResponseLista(Usuario usuario) {
        Link linkUsuario = linkTo(methodOn(UsuarioController.class).findById(usuario.getId())).withRel("Detalhes do usuario");
        Link linkPlano = usuario.getPlano() != null ?
                linkTo(methodOn(PlanoController.class).findById(usuario.getPlano().getId())).withSelfRel() : null;

        return new UsuarioLista(
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getEmail(),
                usuario.getNomeFazenda(),
                usuario.getEstado(),
                usuario.getAreaHectares(),
                usuario.getCultura(),
                linkUsuario,
                linkPlano
        );
    }

}
