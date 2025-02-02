package com.senac.gestao.controllers;

import com.senac.gestao.dtos.*;
import com.senac.gestao.services.PessoaService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaResponse> criar(@RequestBody PessoaRequest request) {
        PessoaResponse response = pessoaService.criar(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizar(@PathVariable UUID id, @RequestBody PessoaRequest request) {
        PessoaResponse response = pessoaService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable UUID id) {
        PessoaResponse response = pessoaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<PessoaResponse>> listarTodos(Pageable pageable) {
        Page<PessoaResponse> response = pessoaService.listarTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity<Void> adicionarEndereco(@PathVariable UUID id, @RequestBody EnderecoRequest request) {
        pessoaService.adicionarEndereco(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/enderecos/{enderecoId}")
    public ResponseEntity<Void> removerEndereco(@PathVariable UUID id, @PathVariable UUID enderecoId) {
        pessoaService.removerEndereco(id, enderecoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/documentos")
    public ResponseEntity<Void> adicionarDocumento(@PathVariable UUID id, @RequestBody DocumentoRequest request) {
        pessoaService.adicionarDocumento(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/documentos/{documentoId}")
    public ResponseEntity<Void> removerDocumento(@PathVariable UUID id, @PathVariable UUID documentoId) {
        pessoaService.removerDocumento(id, documentoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/contatos")
    public ResponseEntity<Void> adicionarContato(@PathVariable UUID id, @RequestBody ContatoRequest request) {
        pessoaService.adicionarContato(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/contatos/{contatoId}")
    public ResponseEntity<Void> removerContato(@PathVariable UUID id, @PathVariable UUID contatoId) {
        pessoaService.removerContato(id, contatoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        pessoaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
