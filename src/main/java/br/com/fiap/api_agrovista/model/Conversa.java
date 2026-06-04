package br.com.fiap.api_agrovista.model;

import br.com.fiap.api_agrovista.model.enums.CanalConversa;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TBL_CONVERSA")
public class Conversa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_conversa")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "canal")
    private CanalConversa canal;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "conversa", cascade = CascadeType.ALL)
    private List<Mensagem> mensagens = new ArrayList<>();

    public Conversa() {
    }

    public Conversa(UUID id, Usuario usuario, CanalConversa canal, LocalDateTime createdAt, List<Mensagem> mensagens) {
        this.id = id;
        this.usuario = usuario;
        this.canal = canal;
        this.createdAt = createdAt;
        this.mensagens = mensagens;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CanalConversa getCanal() {
        return canal;
    }

    public void setCanal(CanalConversa canal) {
        this.canal = canal;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }
}