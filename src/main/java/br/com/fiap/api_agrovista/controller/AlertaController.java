package br.com.fiap.api_agrovista.controller;

import br.com.fiap.api_agrovista.dto.alerta.AlertaRequest;
import br.com.fiap.api_agrovista.dto.alerta.AlertaResponse;
import br.com.fiap.api_agrovista.service.AlertaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/alerta")
@SecurityRequirement(name = "Bearer Authentication")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @PostMapping
    public ResponseEntity<AlertaResponse> create(
            @RequestBody @Valid AlertaRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alertaService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponse> findById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(alertaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AlertaResponse>> findAll(
            Pageable pageable) {

        return ResponseEntity.ok(alertaService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid AlertaRequest request) {

        return ResponseEntity.ok(alertaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        alertaService.delete(id);

        return ResponseEntity.noContent().build();
    }

}