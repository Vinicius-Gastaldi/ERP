package com.senac.gestao.controllers;

import com.senac.gestao.dtos.FornecedorRequest;
import com.senac.gestao.dtos.FornecedorResponse;
import com.senac.gestao.services.FornecedorService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ResponseEntity<FornecedorResponse> criar(@RequestBody FornecedorRequest request) {
        FornecedorResponse response = fornecedorService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponse> atualizar(@PathVariable UUID id, @RequestBody FornecedorRequest request) {
        FornecedorResponse response = fornecedorService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponse> buscarPorId(@PathVariable UUID id) {
        FornecedorResponse response = fornecedorService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<FornecedorResponse>> listarTodos(Pageable pageable) {
        Page<FornecedorResponse> responses = fornecedorService.listarTodos(pageable);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/{fornecedorId}/produtos/{produtoId}")
    public ResponseEntity<Void> adicionarProduto(@PathVariable UUID fornecedorId, @PathVariable UUID produtoId) {
        fornecedorService.adicionarProduto(fornecedorId, produtoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{fornecedorId}/produtos/{produtoId}")
    public ResponseEntity<Void> removerProduto(@PathVariable UUID fornecedorId, @PathVariable UUID produtoId) {
        fornecedorService.removerProduto(fornecedorId, produtoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
