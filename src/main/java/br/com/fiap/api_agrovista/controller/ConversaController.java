package br.com.fiap.api_agrovista.controller;

import br.com.fiap.api_agrovista.dto.conversa.ConversaRequest;
import br.com.fiap.api_agrovista.dto.conversa.ConversaResponse;
import br.com.fiap.api_agrovista.service.ConversaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/conversa")
@SecurityRequirement(name = "Bearer Authentication")
public class ConversaController {

    private final ConversaService conversaService;

    public ConversaController(ConversaService conversaService) {
        this.conversaService = conversaService;
    }

    @PostMapping
    public ResponseEntity<ConversaResponse> create(@RequestBody @Valid ConversaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conversaService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversaResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(conversaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ConversaResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(conversaService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversaResponse> update(@PathVariable UUID id,
                                                   @RequestBody @Valid ConversaRequest request) {
        return ResponseEntity.ok(conversaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        conversaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}