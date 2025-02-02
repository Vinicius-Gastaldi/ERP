package com.senac.gestao.controllers;

import com.senac.gestao.dtos.EnderecoRequest;
import com.senac.gestao.dtos.EnderecoResponse;
import com.senac.gestao.services.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
public class EnderecoController extends BaseController<EnderecoResponse, EnderecoRequest, UUID> {

    private final EnderecoService enderecoService;

    @Override
    public ResponseEntity<EnderecoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(enderecoService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<EnderecoResponse> criar(@Valid @RequestBody EnderecoRequest request) {
        EnderecoResponse response = enderecoService.criar(request);
        return ResponseEntity.created(URI.create("/api/v1/enderecos/" + response.id())).body(response);
    }

    @Override
    public ResponseEntity<EnderecoResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody EnderecoRequest request) {
        return ResponseEntity.ok(enderecoService.atualizar(id, request));
    }

    @Override
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Page<EnderecoResponse>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(enderecoService.listarPorPessoa(null, pageable));
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<Page<EnderecoResponse>> listarPorPessoa(@PathVariable UUID pessoaId, Pageable pageable) {
        return ResponseEntity.ok(enderecoService.listarPorPessoa(pessoaId, pageable));
    }
}
