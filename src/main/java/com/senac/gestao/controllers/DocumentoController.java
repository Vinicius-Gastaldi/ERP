package com.senac.gestao.controllers;

import com.senac.gestao.dtos.DocumentoRequest;
import com.senac.gestao.dtos.DocumentoResponse;
import com.senac.gestao.services.DocumentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documentos")
@RequiredArgsConstructor
public class DocumentoController extends BaseController<DocumentoResponse, DocumentoRequest, UUID> {

    private final DocumentoService documentoService;

    @Override
    public ResponseEntity<Page<DocumentoResponse>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(documentoService.listarPorPessoa(null, pageable));
    }

    @Override
    public ResponseEntity<DocumentoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(documentoService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<DocumentoResponse> criar(@Valid @RequestBody DocumentoRequest request) {
        DocumentoResponse response = documentoService.criar(request);
        return ResponseEntity.created(URI.create("/api/v1/documentos/" + response.id())).body(response);
    }

    @Override
    public ResponseEntity<DocumentoResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody DocumentoRequest request) {
        return ResponseEntity.ok(documentoService.atualizar(id, request));
    }

    @Override
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        documentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<Page<DocumentoResponse>> listarPorPessoa(@PathVariable UUID pessoaId, Pageable pageable) {
        return ResponseEntity.ok(documentoService.listarPorPessoa(pessoaId, pageable));
    }
}
