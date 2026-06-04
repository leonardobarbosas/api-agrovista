package br.com.fiap.api_agrovista.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TBL_PLANO")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_plano")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco_mensal", precision = 10, scale = 2)
    private BigDecimal precoMensal;

    @Column(name = "destaque")
    private boolean destaque;

    @Column(name = "subtexto")
    private String subtexto;

    @OneToMany(mappedBy = "plano")
    private List<Usuario> usuarios;

    public Plano() {
    }

    public Plano(UUID id, String nome, BigDecimal precoMensal, boolean destaque, String subtexto, List<Usuario> usuarios) {
        this.id = id;
        this.nome = nome;
        this.precoMensal = precoMensal;
        this.destaque = destaque;
        this.subtexto = subtexto;
        this.usuarios = usuarios;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoMensal() {
        return precoMensal;
    }

    public void setPrecoMensal(BigDecimal precoMensal) {
        this.precoMensal = precoMensal;
    }

    public boolean isDestaque() {
        return destaque;
    }

    public void setDestaque(boolean destaque) {
        this.destaque = destaque;
    }

    public String getSubtexto() {
        return subtexto;
    }

    public void setSubtexto(String subtexto) {
        this.subtexto = subtexto;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
