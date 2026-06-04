package br.com.fiap.api_agrovista.model;

import br.com.fiap.api_agrovista.model.enums.TipoMensagem;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TBL_MENSAGEM")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_mensagem")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "conversa_id", nullable = false)
    private Conversa conversa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoMensagem tipo;

    @Column(name = "conteudo", columnDefinition = "CLOB")
    private String conteudo;

    @Column(name = "arquivo_nome")
    private String arquivoNome;

    @CreationTimestamp
    @Column(name = "enviado_em")
    private LocalDateTime enviadoEm;

    public Mensagem() {
    }

    public Mensagem(UUID id, Conversa conversa, TipoMensagem tipo, String conteudo, String arquivoNome, LocalDateTime enviadoEm) {
        this.id = id;
        this.conversa = conversa;
        this.tipo = tipo;
        this.conteudo = conteudo;
        this.arquivoNome = arquivoNome;
        this.enviadoEm = enviadoEm;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Conversa getConversa() {
        return conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public TipoMensagem getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensagem tipo) {
        this.tipo = tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getArquivoNome() {
        return arquivoNome;
    }

    public void setArquivoNome(String arquivoNome) {
        this.arquivoNome = arquivoNome;
    }

    public LocalDateTime getEnviadoEm() {
        return enviadoEm;
    }

    public void setEnviadoEm(LocalDateTime enviadoEm) {
        this.enviadoEm = enviadoEm;
    }
}