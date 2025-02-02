package com.senac.gestao.controllers;

import com.senac.gestao.dtos.OrdemProducaoRequest;
import com.senac.gestao.dtos.OrdemProducaoResponse;
import com.senac.gestao.services.OrdemProducaoService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/ordens-producao")
public class OrdemProducaoController {

    private final OrdemProducaoService ordemProducaoService;

    public OrdemProducaoController(OrdemProducaoService ordemProducaoService) {
        this.ordemProducaoService = ordemProducaoService;
    }

    @PostMapping
    public ResponseEntity<OrdemProducaoResponse> criar(@RequestBody OrdemProducaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordemProducaoService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemProducaoResponse> atualizar(@PathVariable UUID id, @RequestBody OrdemProducaoRequest request) {
        return ResponseEntity.ok(ordemProducaoService.atualizar(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemProducaoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ordemProducaoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<OrdemProducaoResponse>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(ordemProducaoService.listarTodos(pageable));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarOrdem(@PathVariable UUID id) {
        ordemProducaoService.cancelarOrdem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<Void> concluirOrdem(@PathVariable UUID id) {
        ordemProducaoService.concluirOrdem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        ordemProducaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
