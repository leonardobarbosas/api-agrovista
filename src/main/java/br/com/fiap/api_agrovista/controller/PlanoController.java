package br.com.fiap.api_agrovista.controller;

import br.com.fiap.api_agrovista.dto.plano.PlanoRequest;
import br.com.fiap.api_agrovista.dto.plano.PlanoResponse;
import br.com.fiap.api_agrovista.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/plano")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @PostMapping
    public ResponseEntity<PlanoResponse> create(@RequestBody @Valid PlanoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planoService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(planoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PlanoResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(planoService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoResponse> update(@PathVariable UUID id, @RequestBody @Valid PlanoRequest request) {
        return ResponseEntity.ok(planoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        planoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}