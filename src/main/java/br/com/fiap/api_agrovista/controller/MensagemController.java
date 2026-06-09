package br.com.fiap.api_agrovista.controller;

import br.com.fiap.api_agrovista.dto.mensagem.MensagemRequest;
import br.com.fiap.api_agrovista.dto.mensagem.MensagemResponse;
import br.com.fiap.api_agrovista.service.MensagemService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mensagem")
@SecurityRequirement(name = "Bearer Authentication")
public class MensagemController {

    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @PostMapping
    public ResponseEntity<MensagemResponse> create(@RequestBody @Valid MensagemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagemService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensagemResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(mensagemService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<MensagemResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(mensagemService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid MensagemRequest request) {

        return ResponseEntity.ok(mensagemService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        mensagemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}