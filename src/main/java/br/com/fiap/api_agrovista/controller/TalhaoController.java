package br.com.fiap.api_agrovista.controller;

import br.com.fiap.api_agrovista.dto.talhao.TalhaoLista;
import br.com.fiap.api_agrovista.dto.talhao.TalhaoRequest;
import br.com.fiap.api_agrovista.dto.talhao.TalhaoResponse;
import br.com.fiap.api_agrovista.service.TalhaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/talhao")
public class TalhaoController {

    private final TalhaoService talhaoService;

    public TalhaoController(TalhaoService talhaoService) {
        this.talhaoService = talhaoService;
    }

    @PostMapping
    public ResponseEntity<TalhaoResponse> create(@RequestBody @Valid TalhaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(talhaoService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TalhaoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(talhaoService.findById(id));
    }

    @Operation(summary = "Busca todos os talhoes por página")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Página de talhoes retornada com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TalhaoLista.class))),
            @ApiResponse(responseCode = "404",
                    description = "Nenhuma cidade encontrado!",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping
    public ResponseEntity<Page<TalhaoLista>> findAll(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by("nome").ascending());
        Page<TalhaoLista> talhao = talhaoService.findAll(pageable);
        if (talhao.isEmpty()) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(talhao, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TalhaoResponse> update(@PathVariable UUID id, @RequestBody @Valid TalhaoRequest request) {
        return ResponseEntity.ok(talhaoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        talhaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}