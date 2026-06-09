package br.com.fiap.api_agrovista.service;

import br.com.fiap.api_agrovista.dto.laudo.LaudoRequest;
import br.com.fiap.api_agrovista.dto.laudo.LaudoResponse;
import br.com.fiap.api_agrovista.mapper.LaudoMapper;
import br.com.fiap.api_agrovista.model.Alerta;
import br.com.fiap.api_agrovista.model.Laudo;
import br.com.fiap.api_agrovista.model.Talhao;
import br.com.fiap.api_agrovista.repository.AlertaRepository;
import br.com.fiap.api_agrovista.repository.LaudoRepository;
import br.com.fiap.api_agrovista.repository.TalhaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LaudoService {

    private final LaudoRepository laudoRepository;
    private final TalhaoRepository talhaoRepository;
    private final AlertaRepository alertaRepository;
    private final LaudoMapper laudoMapper;

    @Autowired
    public LaudoService(
            LaudoRepository laudoRepository,
            TalhaoRepository talhaoRepository,
            AlertaRepository alertaRepository,
            LaudoMapper laudoMapper) {

        this.laudoRepository = laudoRepository;
        this.talhaoRepository = talhaoRepository;
        this.alertaRepository = alertaRepository;
        this.laudoMapper = laudoMapper;
    }

    public LaudoResponse create(LaudoRequest request) {

        Talhao talhao = talhaoRepository.findById(request.idTalhao())
                .orElseThrow(() -> new EntityNotFoundException("Talhão não encontrado"));

        Alerta alerta = null;

        if (request.idAlerta() != null) {
            alerta = alertaRepository.findById(request.idAlerta())
                    .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));
        }

        Laudo laudo = new Laudo();

        laudo.setTalhao(talhao);
        laudo.setAlerta(alerta);
        laudo.setEvento(request.evento());
        laudo.setStatus(request.status());
        laudo.setTamanhoMb(request.tamanhoMb());

        return laudoMapper.laudoToResponse(laudoRepository.save(laudo));
    }

    public LaudoResponse findById(UUID id) {

        Laudo laudo = laudoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Laudo não encontrado"));

        return laudoMapper.laudoToResponse(laudo);
    }

    public Page<LaudoResponse> findAll(Pageable pageable) {

        return laudoRepository.findAll(pageable)
                .map(laudoMapper::laudoToResponse);
    }

    public LaudoResponse update(UUID id, LaudoRequest request) {

        Laudo laudo = laudoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Laudo não encontrado"));

        Talhao talhao = talhaoRepository.findById(request.idTalhao())
                .orElseThrow(() -> new EntityNotFoundException("Talhão não encontrado"));

        Alerta alerta = null;

        if (request.idAlerta() != null) {
            alerta = alertaRepository.findById(request.idAlerta())
                    .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));
        }

        laudo.setTalhao(talhao);
        laudo.setAlerta(alerta);
        laudo.setEvento(request.evento());
        laudo.setStatus(request.status());
        laudo.setTamanhoMb(request.tamanhoMb());

        return laudoMapper.laudoToResponse(laudoRepository.save(laudo));
    }

    public void delete(UUID id) {

        if (!laudoRepository.existsById(id)) {
            throw new EntityNotFoundException("Laudo não encontrado");
        }

        laudoRepository.deleteById(id);
    }

}