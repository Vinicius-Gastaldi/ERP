package com.senac.gestao.controllers;

import com.senac.gestao.dtos.MovimentacaoRequest;
import com.senac.gestao.dtos.MovimentacaoResponse;
import com.senac.gestao.services.MovimentacaoEstoqueService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/movimentacoes")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoEstoqueService;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueService movimentacaoEstoqueService) {
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
    }

    @PostMapping
    public ResponseEntity<MovimentacaoResponse> criar(@RequestBody MovimentacaoRequest request) {
        MovimentacaoResponse response = movimentacaoEstoqueService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/reverter")
    public ResponseEntity<MovimentacaoResponse> reverter(@PathVariable UUID id) {
        MovimentacaoResponse response = movimentacaoEstoqueService.reverter(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoResponse> buscarPorId(@PathVariable UUID id) {
        MovimentacaoResponse response = movimentacaoEstoqueService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/estoque/{estoqueId}")
    public ResponseEntity<Page<MovimentacaoResponse>> listarPorEstoque(@PathVariable UUID estoqueId, Pageable pageable) {
        Page<MovimentacaoResponse> responses = movimentacaoEstoqueService.listarPorEstoque(estoqueId, pageable);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<Page<MovimentacaoResponse>> listarPorProduto(@PathVariable UUID produtoId, Pageable pageable) {
        Page<MovimentacaoResponse> responses = movimentacaoEstoqueService.listarPorProduto(produtoId, pageable);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        movimentacaoEstoqueService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
