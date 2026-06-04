package br.com.fiap.api_agrovista.model;

import br.com.fiap.api_agrovista.model.enums.StatusTalhao;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TBL_TALHAO")
public class Talhao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_talhao")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cultura", nullable = false)
    private String cultura;

    @Column(name = "area_ha", nullable = false)
    private double areaHa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTalhao status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "talhao", cascade = CascadeType.ALL)
    private List<Alerta> alertas = new ArrayList<>();

    @OneToMany(mappedBy = "talhao", cascade = CascadeType.ALL)
    private List<Laudo> laudos = new ArrayList<>();

    public Talhao() {
    }

    public Talhao(UUID id, String nome, String cultura, double areaHa, StatusTalhao status, Usuario usuario, List<Alerta> alertas, List<Laudo> laudos) {
        this.id = id;
        this.nome = nome;
        this.cultura = cultura;
        this.areaHa = areaHa;
        this.status = status;
        this.usuario = usuario;
        this.alertas = alertas;
        this.laudos = laudos;
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

    public String getCultura() {
        return cultura;
    }

    public void setCultura(String cultura) {
        this.cultura = cultura;
    }

    public double getAreaHa() {
        return areaHa;
    }

    public void setAreaHa(double areaHa) {
        this.areaHa = areaHa;
    }

    public StatusTalhao getStatus() {
        return status;
    }

    public void setStatus(StatusTalhao status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }

    public List<Laudo> getLaudos() {
        return laudos;
    }

    public void setLaudos(List<Laudo> laudos) {
        this.laudos = laudos;
    }
}