package br.com.fiap.api_agrovista.dto.plano;

import org.springframework.hateoas.Link;

import java.math.BigDecimal;

public record PlanoLista(String nome, BigDecimal preco, String subtexto, Link link){
}
