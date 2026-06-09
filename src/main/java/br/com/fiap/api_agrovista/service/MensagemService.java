package br.com.fiap.api_agrovista.service;

import br.com.fiap.api_agrovista.dto.mensagem.MensagemRequest;
import br.com.fiap.api_agrovista.dto.mensagem.MensagemResponse;
import br.com.fiap.api_agrovista.mapper.MensagemMapper;
import br.com.fiap.api_agrovista.model.Conversa;
import br.com.fiap.api_agrovista.model.Mensagem;
import br.com.fiap.api_agrovista.repository.ConversaRepository;
import br.com.fiap.api_agrovista.repository.MensagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final ConversaRepository conversaRepository;
    private final MensagemMapper mensagemMapper;

    @Autowired
    public MensagemService(
            MensagemRepository mensagemRepository,
            ConversaRepository conversaRepository,
            MensagemMapper mensagemMapper) {

        this.mensagemRepository = mensagemRepository;
        this.conversaRepository = conversaRepository;
        this.mensagemMapper = mensagemMapper;
    }

    public MensagemResponse create(MensagemRequest request) {

        Conversa conversa = conversaRepository.findById(request.idConversa())
                .orElseThrow(() -> new EntityNotFoundException("Conversa não encontrada"));

        Mensagem mensagem = new Mensagem();

        mensagem.setConversa(conversa);
        mensagem.setTipo(request.tipo());
        mensagem.setConteudo(request.conteudo());
        mensagem.setArquivoNome(request.arquivoNome());

        return mensagemMapper.mensagemToResponse(mensagemRepository.save(mensagem));
    }

    public MensagemResponse findById(UUID id) {

        Mensagem mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mensagem não encontrada"));

        return mensagemMapper.mensagemToResponse(mensagem);
    }

    public Page<MensagemResponse> findAll(Pageable pageable) {

        return mensagemRepository
                .findAll(pageable)
                .map(mensagemMapper::mensagemToResponse);
    }

    public MensagemResponse update(UUID id, MensagemRequest request) {

        Mensagem mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mensagem não encontrada"));

        Conversa conversa = conversaRepository.findById(request.idConversa())
                .orElseThrow(() -> new EntityNotFoundException("Conversa não encontrada"));

        mensagem.setConversa(conversa);
        mensagem.setTipo(request.tipo());
        mensagem.setConteudo(request.conteudo());
        mensagem.setArquivoNome(request.arquivoNome());

        return mensagemMapper.mensagemToResponse(mensagemRepository.save(mensagem));
    }

    public void delete(UUID id) {

        if (!mensagemRepository.existsById(id)) {
            throw new EntityNotFoundException("Mensagem não encontrada");
        }

        mensagemRepository.deleteById(id);
    }
}