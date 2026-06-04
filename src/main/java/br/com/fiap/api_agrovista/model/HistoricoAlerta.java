package br.com.fiap.api_agrovista.model;

import br.com.fiap.api_agrovista.model.enums.ResultadoAlerta;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "TBL_HISTORICO_ALERTA")
public class HistoricoAlerta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_historico")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultado")
    private ResultadoAlerta resultado;

    @Column(name = "descricao", columnDefinition = "CLOB")
    private String descricao;

    @Column(name = "data_evento")
    private LocalDate dataEvento;

    @ManyToOne
    @JoinColumn(name = "alerta_id", nullable = false)
    private Alerta alerta;

    @ManyToOne
    @JoinColumn(name = "talhao_id", nullable = false)
    private Talhao talhao;

    public HistoricoAlerta() {
    }

    public HistoricoAlerta(Talhao talhao, Alerta alerta, LocalDate dataEvento, String descricao, ResultadoAlerta resultado, UUID id) {
        this.talhao = talhao;
        this.alerta = alerta;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.resultado = resultado;
        this.id = id;
    }

    public Talhao getTalhao() {
        return talhao;
    }

    public void setTalhao(Talhao talhao) {
        this.talhao = talhao;
    }

    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ResultadoAlerta getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoAlerta resultado) {
        this.resultado = resultado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}