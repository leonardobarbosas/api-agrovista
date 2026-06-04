package br.com.fiap.api_agrovista.dto.usuario;

import org.springframework.hateoas.Link;

public record UsuarioLista(String nome, String cpf, String telefone, String email, String nomeFazenda, String estado, double areaHectares, String cultura, Link linkUsuario, Link linkPlano){
}
