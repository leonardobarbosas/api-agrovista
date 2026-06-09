package br.com.fiap.api_agrovista.controller;

import br.com.fiap.api_agrovista.dto.historicoAlerta.HistoricoAlertaRequest;
import br.com.fiap.api_agrovista.dto.historicoAlerta.HistoricoAlertaResponse;
import br.com.fiap.api_agrovista.service.HistoricoAlertaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/historico-alerta")
@SecurityRequirement(name = "Bearer Authentication")
public class HistoricoAlertaController {

    private final HistoricoAlertaService historicoService;

    public HistoricoAlertaController(HistoricoAlertaService historicoService) {
        this.historicoService = historicoService;
    }

    @PostMapping
    public ResponseEntity<HistoricoAlertaResponse> create(
            @RequestBody @Valid HistoricoAlertaRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(historicoService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoAlertaResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(historicoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<HistoricoAlertaResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(historicoService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricoAlertaResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid HistoricoAlertaRequest request) {

        return ResponseEntity.ok(historicoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        historicoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}