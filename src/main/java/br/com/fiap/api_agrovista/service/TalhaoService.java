package br.com.fiap.api_agrovista.service;

import br.com.fiap.api_agrovista.dto.talhao.TalhaoLista;
import br.com.fiap.api_agrovista.dto.talhao.TalhaoRequest;
import br.com.fiap.api_agrovista.dto.talhao.TalhaoResponse;
import br.com.fiap.api_agrovista.mapper.TalhaoMapper;
import br.com.fiap.api_agrovista.model.Talhao;
import br.com.fiap.api_agrovista.model.Usuario;
import br.com.fiap.api_agrovista.repository.TalhaoRepository;
import br.com.fiap.api_agrovista.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TalhaoService {

    private final TalhaoRepository talhaoRepository;
    private final TalhaoMapper talhaoMapper;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public TalhaoService(TalhaoRepository talhaoRepository, TalhaoMapper talhaoMapper, UsuarioRepository usuarioRepository) {
        this.talhaoRepository = talhaoRepository;
        this.talhaoMapper = talhaoMapper;
        this.usuarioRepository = usuarioRepository;
    }

    public TalhaoResponse create(TalhaoRequest talhaoRequest) {
        Usuario usuario = usuarioRepository.findById(talhaoRequest.idUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Talhao talhao = new Talhao();
        talhao.setNome(talhaoRequest.nome());
        talhao.setCultura(talhaoRequest.cultura());
        talhao.setAreaHa(talhaoRequest.areaHa());
        talhao.setStatus(talhaoRequest.status());
        talhao.setUsuario(usuario);

        return talhaoMapper.talhaoToResponse(talhaoRepository.save(talhao));
    }

    public TalhaoResponse findById(UUID id) {
        Talhao talhao = talhaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Talhão não encontrado"));
        return talhaoMapper.talhaoToResponse(talhao);
    }

    public Page<TalhaoLista> findAll(Pageable pageable) {
        return talhaoRepository
                .findAll(pageable)
                .map(talhaoMapper::talhaoToResponseLista);
    }

    public TalhaoResponse update(UUID id, TalhaoRequest talhaoRequest) {
        Talhao talhao = talhaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Talhão não encontrado"));

        Usuario usuario = usuarioRepository.findById(talhaoRequest.idUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        talhao.setNome(talhaoRequest.nome());
        talhao.setCultura(talhaoRequest.cultura());
        talhao.setAreaHa(talhaoRequest.areaHa());
        talhao.setStatus(talhaoRequest.status());
        talhao.setUsuario(usuario);

        return talhaoMapper.talhaoToResponse(talhaoRepository.save(talhao));
    }

    public void delete(UUID id) {
        if (!talhaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Talhão não encontrado");
        }
        talhaoRepository.deleteById(id);
    }
}