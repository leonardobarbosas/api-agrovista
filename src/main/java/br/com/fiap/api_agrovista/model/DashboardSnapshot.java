package br.com.fiap.api_agrovista.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TBL_DASHBOARD_SNAPSHOT")
public class DashboardSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_snapshot")
    private UUID id;

    @Column(name = "satelites_ativos", nullable = false)
    private int satelitesAtivos;

    @Column(name = "regioes_monitoradas", nullable = false)
    private int regioesMonitoradas;

    @Column(name = "precisao_ia", nullable = false)
    private BigDecimal precisaoIa;

    @Column(name = "fonte_dados")
    private String fonteDados;

    @CreationTimestamp
    @Column(name = "registrado_em")
    private LocalDateTime registradoEm;

    public DashboardSnapshot() {
    }

    public DashboardSnapshot(UUID id, int satelitesAtivos, int regioesMonitoradas, BigDecimal precisaoIa, String fonteDados, LocalDateTime registradoEm) {
        this.id = id;
        this.satelitesAtivos = satelitesAtivos;
        this.regioesMonitoradas = regioesMonitoradas;
        this.precisaoIa = precisaoIa;
        this.fonteDados = fonteDados;
        this.registradoEm = registradoEm;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getSatelitesAtivos() {
        return satelitesAtivos;
    }

    public void setSatelitesAtivos(int satelitesAtivos) {
        this.satelitesAtivos = satelitesAtivos;
    }

    public int getRegioesMonitoradas() {
        return regioesMonitoradas;
    }

    public void setRegioesMonitoradas(int regioesMonitoradas) {
        this.regioesMonitoradas = regioesMonitoradas;
    }

    public BigDecimal getPrecisaoIa() {
        return precisaoIa;
    }

    public void setPrecisaoIa(BigDecimal precisaoIa) {
        this.precisaoIa = precisaoIa;
    }

    public String getFonteDados() {
        return fonteDados;
    }

    public void setFonteDados(String fonteDados) {
        this.fonteDados = fonteDados;
    }

    public LocalDateTime getRegistradoEm() {
        return registradoEm;
    }

    public void setRegistradoEm(LocalDateTime registradoEm) {
        this.registradoEm = registradoEm;
    }
}