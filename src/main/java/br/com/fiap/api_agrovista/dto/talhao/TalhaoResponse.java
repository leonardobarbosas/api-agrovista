package br.com.fiap.api_agrovista.dto.talhao;

import br.com.fiap.api_agrovista.model.enums.StatusTalhao;
import org.springframework.hateoas.Link;

import java.util.UUID;

public record TalhaoResponse(UUID id, String nome, String cultura, double areaHa, StatusTalhao status, Link linkUsuario, Link linkTalhao) {
}
