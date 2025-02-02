package com.senac.gestao.controllers;

import com.senac.gestao.dtos.LinhaProducaoRequest;
import com.senac.gestao.dtos.LinhaProducaoResponse;
import com.senac.gestao.services.LinhaProducaoService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/linhas-producao")
public class LinhaProducaoController {

    private final LinhaProducaoService linhaProducaoService;

    public LinhaProducaoController(LinhaProducaoService linhaProducaoService) {
        this.linhaProducaoService = linhaProducaoService;
    }

    @PostMapping
    public ResponseEntity<LinhaProducaoResponse> criar(@RequestBody LinhaProducaoRequest request) {
        LinhaProducaoResponse response = linhaProducaoService.criar(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinhaProducaoResponse> atualizar(@PathVariable UUID id, @RequestBody LinhaProducaoRequest request) {
        LinhaProducaoResponse response = linhaProducaoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinhaProducaoResponse> buscarPorId(@PathVariable UUID id) {
        LinhaProducaoResponse response = linhaProducaoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<LinhaProducaoResponse>> listarTodos(Pageable pageable) {
        Page<LinhaProducaoResponse> response = linhaProducaoService.listarTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<Void> iniciarProducao(@PathVariable UUID id) {
        linhaProducaoService.iniciarProducao(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/parar")
    public ResponseEntity<Void> pararProducao(@PathVariable UUID id) {
        linhaProducaoService.pararProducao(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/agendar-manutencao")
    public ResponseEntity<Void> agendarManutencao(@PathVariable UUID id, @RequestParam LocalDateTime data) {
        linhaProducaoService.agendarManutencao(id, data);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        linhaProducaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
