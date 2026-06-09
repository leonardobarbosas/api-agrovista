package br.com.fiap.api_agrovista.service;

import br.com.fiap.api_agrovista.dto.conversa.ConversaRequest;
import br.com.fiap.api_agrovista.dto.conversa.ConversaResponse;
import br.com.fiap.api_agrovista.mapper.ConversaMapper;
import br.com.fiap.api_agrovista.model.Conversa;
import br.com.fiap.api_agrovista.model.Usuario;
import br.com.fiap.api_agrovista.repository.ConversaRepository;
import br.com.fiap.api_agrovista.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConversaService {

    private final ConversaRepository conversaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ConversaMapper conversaMapper;

    @Autowired
    public ConversaService(
            ConversaRepository conversaRepository,
            UsuarioRepository usuarioRepository,
            ConversaMapper conversaMapper) {

        this.conversaRepository = conversaRepository;
        this.usuarioRepository = usuarioRepository;
        this.conversaMapper = conversaMapper;
    }

    public ConversaResponse create(ConversaRequest request) {

        Usuario usuario = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Conversa conversa = new Conversa();

        conversa.setUsuario(usuario);
        conversa.setCanal(request.canal());

        return conversaMapper.conversaToResponse(conversaRepository.save(conversa));
    }

    public ConversaResponse findById(UUID id) {

        Conversa conversa = conversaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conversa não encontrada"));

        return conversaMapper.conversaToResponse(conversa);
    }

    public Page<ConversaResponse> findAll(Pageable pageable) {

        return conversaRepository
                .findAll(pageable)
                .map(conversaMapper::conversaToResponse);
    }

    public ConversaResponse update(UUID id, ConversaRequest request) {

        Conversa conversa = conversaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conversa não encontrada"));

        Usuario usuario = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        conversa.setUsuario(usuario);
        conversa.setCanal(request.canal());

        return conversaMapper.conversaToResponse(conversaRepository.save(conversa));
    }

    public void delete(UUID id) {

        if (!conversaRepository.existsById(id)) {
            throw new EntityNotFoundException("Conversa não encontrada");
        }

        conversaRepository.deleteById(id);
    }

}