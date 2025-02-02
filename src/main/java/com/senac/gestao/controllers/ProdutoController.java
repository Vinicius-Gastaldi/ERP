package com.senac.gestao.controllers;

import com.senac.gestao.dtos.ProdutoRequest;
import com.senac.gestao.dtos.ProdutoResponse;
import com.senac.gestao.models.enums.CategoriaProdutoEnum;
import com.senac.gestao.services.ProdutoService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(@RequestBody ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable UUID id, @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(produtoService.atualizar(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarTodos(pageable));
    }

    @GetMapping("/categoria")
    public ResponseEntity<Page<ProdutoResponse>> listarPorCategoria(@RequestParam CategoriaProdutoEnum categoria, Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarPorCategoria(categoria, pageable));
    }

    @GetMapping("/reposicao")
    public ResponseEntity<List<ProdutoResponse>> listarParaReposicao() {
        return ResponseEntity.ok(produtoService.listarParaReposicao());
    }

    @GetMapping("/excedentes")
    public ResponseEntity<List<ProdutoResponse>> listarExcedentes() {
        return ResponseEntity.ok(produtoService.listarExcedentes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
