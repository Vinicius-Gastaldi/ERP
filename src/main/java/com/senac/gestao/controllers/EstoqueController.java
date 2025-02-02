package com.senac.gestao.controllers;

import com.senac.gestao.dtos.EstoqueRequest;
import com.senac.gestao.dtos.EstoqueResponse;
import com.senac.gestao.services.EstoqueService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<EstoqueResponse> criar(@RequestBody EstoqueRequest request) {
        EstoqueResponse response = estoqueService.criar(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponse> atualizar(@PathVariable UUID id, @RequestBody EstoqueRequest request) {
        EstoqueResponse response = estoqueService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> buscarPorId(@PathVariable UUID id) {
        EstoqueResponse response = estoqueService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<EstoqueResponse>> listarTodos(Pageable pageable) {
        Page<EstoqueResponse> response = estoqueService.listarTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{estoqueId}/produtos/{produtoId}")
    public ResponseEntity<Void> adicionarProduto(@PathVariable UUID estoqueId, @PathVariable UUID produtoId) {
        estoqueService.adicionarProduto(estoqueId, produtoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{estoqueId}/produtos/{produtoId}")
    public ResponseEntity<Void> removerProduto(@PathVariable UUID estoqueId, @PathVariable UUID produtoId) {
        estoqueService.removerProduto(estoqueId, produtoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        estoqueService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
