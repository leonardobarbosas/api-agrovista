package br.com.fiap.api_agrovista.model;

import br.com.fiap.api_agrovista.model.enums.StatusLaudo;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TBL_LAUDO")
public class Laudo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_laudo")
    private UUID id;

    @Column(name = "evento", nullable = false)
    private String evento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusLaudo statusLaudo;

    @Column(name = "tamanho_mb", precision = 6, scale = 2)
    private BigDecimal tamanhoMb;

    @CreationTimestamp
    @Column(name = "gerado_em")
    private LocalDateTime geradoEm;

    @ManyToOne
    @JoinColumn(name = "alerta_id")
    private Alerta alerta;

    @ManyToOne
    @JoinColumn(name = "talhao_id", nullable = false)
    private Talhao talhao;


    public Laudo() {
    }

    public Laudo(LocalDateTime geradoEm, BigDecimal tamanhoMb, StatusLaudo status, String evento, Alerta alerta, Talhao talhao, UUID id) {
        this.geradoEm = geradoEm;
        this.tamanhoMb = tamanhoMb;
        this.statusLaudo = status;
        this.evento = evento;
        this.alerta = alerta;
        this.talhao = talhao;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public StatusLaudo getStatus() {
        return statusLaudo;
    }

    public void setStatus(StatusLaudo statusLaudo) {
        this.statusLaudo = statusLaudo;
    }

    public BigDecimal getTamanhoMb() {
        return tamanhoMb;
    }

    public void setTamanhoMb(BigDecimal tamanhoMb) {
        this.tamanhoMb = tamanhoMb;
    }

    public LocalDateTime getGeradoEm() {
        return geradoEm;
    }

    public void setGeradoEm(LocalDateTime geradoEm) {
        this.geradoEm = geradoEm;
    }
}