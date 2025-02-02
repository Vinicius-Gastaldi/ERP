package com.senac.gestao.controllers;

import com.senac.gestao.dtos.ContatoRequest;
import com.senac.gestao.dtos.ContatoResponse;
import com.senac.gestao.services.ContatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contatos")
@RequiredArgsConstructor
public class ContatoController extends BaseController<ContatoResponse, ContatoRequest, UUID> {

    private final ContatoService contatoService;

    @Override
    public ResponseEntity<Page<ContatoResponse>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(contatoService.listarPorPessoa(null, pageable));
    }

    @Override
    public ResponseEntity<ContatoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(contatoService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<ContatoResponse> criar(@Valid @RequestBody ContatoRequest request) {
        ContatoResponse response = contatoService.criar(request);
        return ResponseEntity.created(URI.create("/api/v1/contatos/" + response.id())).body(response);
    }

    @Override
    public ResponseEntity<ContatoResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody ContatoRequest request) {
        return ResponseEntity.ok(contatoService.atualizar(id, request));
    }

    @Override
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        contatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<Page<ContatoResponse>> listarPorPessoa(@PathVariable UUID pessoaId, Pageable pageable) {
        return ResponseEntity.ok(contatoService.listarPorPessoa(pessoaId, pageable));
    }
}
