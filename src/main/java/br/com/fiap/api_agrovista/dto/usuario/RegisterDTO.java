package br.com.fiap.api_agrovista.dto.usuario;

import br.com.fiap.api_agrovista.model.enums.RoleUsuario;

public record RegisterDTO(String email, String senha, RoleUsuario role) {
}