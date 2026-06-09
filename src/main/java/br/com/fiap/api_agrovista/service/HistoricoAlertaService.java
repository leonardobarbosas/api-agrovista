package br.com.fiap.api_agrovista.service;

import br.com.fiap.api_agrovista.dto.historicoAlerta.HistoricoAlertaRequest;
import br.com.fiap.api_agrovista.dto.historicoAlerta.HistoricoAlertaResponse;
import br.com.fiap.api_agrovista.mapper.HistoricoAlertaMapper;
import br.com.fiap.api_agrovista.model.Alerta;
import br.com.fiap.api_agrovista.model.HistoricoAlerta;
import br.com.fiap.api_agrovista.model.Talhao;
import br.com.fiap.api_agrovista.repository.AlertaRepository;
import br.com.fiap.api_agrovista.repository.HistoricoAlertaRepository;
import br.com.fiap.api_agrovista.repository.TalhaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HistoricoAlertaService {

    private final HistoricoAlertaRepository historicoRepository;
    private final AlertaRepository alertaRepository;
    private final TalhaoRepository talhaoRepository;
    private final HistoricoAlertaMapper historicoMapper;

    @Autowired
    public HistoricoAlertaService(
            HistoricoAlertaRepository historicoRepository,
            AlertaRepository alertaRepository,
            TalhaoRepository talhaoRepository,
            HistoricoAlertaMapper historicoMapper) {

        this.historicoRepository = historicoRepository;
        this.alertaRepository = alertaRepository;
        this.talhaoRepository = talhaoRepository;
        this.historicoMapper = historicoMapper;
    }

    public HistoricoAlertaResponse create(HistoricoAlertaRequest request) {

        Alerta alerta = alertaRepository.findById(request.idAlerta())
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));

        Talhao talhao = talhaoRepository.findById(request.idTalhao())
                .orElseThrow(() -> new EntityNotFoundException("Talhão não encontrado"));

        HistoricoAlerta historico = new HistoricoAlerta();

        historico.setAlerta(alerta);
        historico.setTalhao(talhao);
        historico.setResultado(request.resultado());
        historico.setDescricao(request.descricao());
        historico.setDataEvento(request.dataEvento());

        return historicoMapper.historicoToResponse(historicoRepository.save(historico));
    }

    public HistoricoAlertaResponse findById(UUID id) {

        HistoricoAlerta historico = historicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico não encontrado"));

        return historicoMapper.historicoToResponse(historico);
    }

    public Page<HistoricoAlertaResponse> findAll(Pageable pageable) {

        return historicoRepository.findAll(pageable)
                .map(historicoMapper::historicoToResponse);
    }

    public HistoricoAlertaResponse update(UUID id, HistoricoAlertaRequest request) {

        HistoricoAlerta historico = historicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico não encontrado"));

        Alerta alerta = alertaRepository.findById(request.idAlerta())
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));

        Talhao talhao = talhaoRepository.findById(request.idTalhao())
                .orElseThrow(() -> new EntityNotFoundException("Talhão não encontrado"));

        historico.setAlerta(alerta);
        historico.setTalhao(talhao);
        historico.setResultado(request.resultado());
        historico.setDescricao(request.descricao());
        historico.setDataEvento(request.dataEvento());

        return historicoMapper.historicoToResponse(historicoRepository.save(historico));
    }

    public void delete(UUID id) {

        if (!historicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Histórico não encontrado");
        }

        historicoRepository.deleteById(id);
    }
}