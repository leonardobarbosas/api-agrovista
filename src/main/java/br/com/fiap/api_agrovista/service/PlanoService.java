package br.com.fiap.api_agrovista.service;

import br.com.fiap.api_agrovista.dto.plano.PlanoRequest;
import br.com.fiap.api_agrovista.dto.plano.PlanoResponse;
import br.com.fiap.api_agrovista.mapper.PlanoMapper;
import br.com.fiap.api_agrovista.model.Plano;
import br.com.fiap.api_agrovista.repository.PlanoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;
    private final PlanoMapper planoMapper;

    @Autowired
    public PlanoService(PlanoRepository planoRepository, PlanoMapper planoMapper) {
        this.planoRepository = planoRepository;
        this.planoMapper = planoMapper;
    }

    public PlanoResponse create(PlanoRequest planoRequest) {
        Plano plano = new Plano();
        plano.setNome(planoRequest.nome());
        plano.setPrecoMensal(planoRequest.precoMensal());
        plano.setDestaque(planoRequest.destaque());
        plano.setSubtexto(planoRequest.subtexto());

        return planoMapper.planoToResponse(planoRepository.save(plano));
    }

    public PlanoResponse findById(UUID id) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));
        return planoMapper.planoToResponse(plano);
    }

    public Page<PlanoResponse> findAll(Pageable pageable) {
        return planoRepository
                .findAll(pageable)
                .map(planoMapper::planoToResponse);
    }

    public PlanoResponse update(UUID id, PlanoRequest planoRequest) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));

        plano.setNome(planoRequest.nome());
        plano.setPrecoMensal(planoRequest.precoMensal());
        plano.setDestaque(planoRequest.destaque());
        plano.setSubtexto(planoRequest.subtexto());

        return planoMapper.planoToResponse(planoRepository.save(plano));
    }

    public void delete(UUID id) {
        if (!planoRepository.existsById(id)) {
            throw new EntityNotFoundException("Plano não encontrado");
        }
        planoRepository.deleteById(id);
    }
}