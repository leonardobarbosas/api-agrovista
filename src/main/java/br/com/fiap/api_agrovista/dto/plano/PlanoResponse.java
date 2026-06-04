package br.com.fiap.api_agrovista.dto.plano;

import org.springframework.hateoas.Link;

import java.math.BigDecimal;
import java.util.UUID;

public record PlanoResponse(UUID id, String nome, BigDecimal precoMensal, String subtexto, Link link) {
}
