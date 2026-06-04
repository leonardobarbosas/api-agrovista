package br.com.fiap.api_agrovista.model;

import br.com.fiap.api_agrovista.model.enums.NivelAlerta;
import br.com.fiap.api_agrovista.model.enums.TipoAlerta;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TBL_ALERTA")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_alerta")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoAlerta tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private NivelAlerta nivel;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "descricao", columnDefinition = "CLOB")
    private String descricao;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "talhao_id", nullable = false)
    private Talhao talhao;

    @OneToMany(mappedBy = "alerta", cascade = CascadeType.ALL)
    private List<HistoricoAlerta> historicos = new ArrayList<>();

    @OneToMany(mappedBy = "alerta", cascade = CascadeType.ALL)
    private List<Laudo> laudos = new ArrayList<>();

    public Alerta() {
    }

    public Alerta(UUID id, TipoAlerta tipo, NivelAlerta nivel, boolean ativo, String descricao, LocalDateTime createdAt, Talhao talhao, List<HistoricoAlerta> historicos, List<Laudo> laudos) {
        this.id = id;
        this.tipo = tipo;
        this.nivel = nivel;
        this.ativo = ativo;
        this.descricao = descricao;
        this.createdAt = createdAt;
        this.talhao = talhao;
        this.historicos = historicos;
        this.laudos = laudos;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TipoAlerta getTipo() {
        return tipo;
    }

    public void setTipo(TipoAlerta tipo) {
        this.tipo = tipo;
    }

    public NivelAlerta getNivel() {
        return nivel;
    }

    public void setNivel(NivelAlerta nivel) {
        this.nivel = nivel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Talhao getTalhao() {
        return talhao;
    }

    public void setTalhao(Talhao talhao) {
        this.talhao = talhao;
    }

    public List<HistoricoAlerta> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<HistoricoAlerta> historicos) {
        this.historicos = historicos;
    }

    public List<Laudo> getLaudos() {
        return laudos;
    }

    public void setLaudos(List<Laudo> laudos) {
        this.laudos = laudos;
    }
}