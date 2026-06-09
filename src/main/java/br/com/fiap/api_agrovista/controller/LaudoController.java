package br.com.fiap.api_agrovista.controller;

import br.com.fiap.api_agrovista.dto.laudo.LaudoRequest;
import br.com.fiap.api_agrovista.dto.laudo.LaudoResponse;
import br.com.fiap.api_agrovista.service.LaudoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/laudo")
@SecurityRequirement(name = "Bearer Authentication")
public class LaudoController {

    private final LaudoService laudoService;

    public LaudoController(LaudoService laudoService) {
        this.laudoService = laudoService;
    }

    @PostMapping
    public ResponseEntity<LaudoResponse> create(@RequestBody @Valid LaudoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(laudoService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaudoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(laudoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<LaudoResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(laudoService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaudoResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid LaudoRequest request) {

        return ResponseEntity.ok(laudoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        laudoService.delete(id);

        return ResponseEntity.noContent().build();
    }

}