package br.com.fiap.api_agrovista.service;

import br.com.fiap.api_agrovista.dto.alerta.AlertaRequest;
import br.com.fiap.api_agrovista.dto.alerta.AlertaResponse;
import br.com.fiap.api_agrovista.mapper.AlertaMapper;
import br.com.fiap.api_agrovista.model.Alerta;
import br.com.fiap.api_agrovista.model.Talhao;
import br.com.fiap.api_agrovista.repository.AlertaRepository;
import br.com.fiap.api_agrovista.repository.TalhaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final TalhaoRepository talhaoRepository;
    private final AlertaMapper alertaMapper;

    @Autowired
    public AlertaService(
            AlertaRepository alertaRepository,
            TalhaoRepository talhaoRepository,
            AlertaMapper alertaMapper) {

        this.alertaRepository = alertaRepository;
        this.talhaoRepository = talhaoRepository;
        this.alertaMapper = alertaMapper;
    }

    public AlertaResponse create(AlertaRequest request) {

        Talhao talhao = talhaoRepository.findById(request.idTalhao())
                .orElseThrow(() ->
                        new EntityNotFoundException("Talhão não encontrado"));

        Alerta alerta = new Alerta();

        alerta.setTalhao(talhao);
        alerta.setTipo(request.tipo());
        alerta.setNivel(request.nivel());
        alerta.setAtivo(request.ativo());
        alerta.setDescricao(request.descricao());

        return alertaMapper.alertaToResponse(alertaRepository.save(alerta));
    }

    public AlertaResponse findById(UUID id) {

        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Alerta não encontrado"));

        return alertaMapper.alertaToResponse(alerta);
    }

    public Page<AlertaResponse> findAll(Pageable pageable) {

        return alertaRepository.findAll(pageable)
                .map(alertaMapper::alertaToResponse);
    }

    public AlertaResponse update(UUID id, AlertaRequest request) {

        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Alerta não encontrado"));

        Talhao talhao = talhaoRepository.findById(request.idTalhao())
                .orElseThrow(() ->
                        new EntityNotFoundException("Talhão não encontrado"));

        alerta.setTalhao(talhao);
        alerta.setTipo(request.tipo());
        alerta.setNivel(request.nivel());
        alerta.setAtivo(request.ativo());
        alerta.setDescricao(request.descricao());

        return alertaMapper.alertaToResponse(alertaRepository.save(alerta));
    }

    public void delete(UUID id) {

        if (!alertaRepository.existsById(id)) {
            throw new EntityNotFoundException("Alerta não encontrado");
        }

        alertaRepository.deleteById(id);
    }

}