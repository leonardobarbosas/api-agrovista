package br.com.fiap.api_agrovista.dto.usuario;

import org.springframework.hateoas.Link;

import java.util.UUID;

public record UsuarioResponse(UUID id, String nome, String cpf, String telefone, String email, Link linkPlano) {
}
