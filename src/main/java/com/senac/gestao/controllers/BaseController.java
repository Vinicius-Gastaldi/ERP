package com.senac.gestao.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/api/v1")
public abstract class BaseController<ResponseDTO, RequestDTO, ID> {

    @GetMapping
    public abstract ResponseEntity<Page<ResponseDTO>> listarTodos(Pageable pageable);

    @GetMapping("/{id}")
    public abstract ResponseEntity<ResponseDTO> buscarPorId(@PathVariable ID id);

    @PostMapping
    public abstract ResponseEntity<ResponseDTO> criar(@Valid @RequestBody RequestDTO request);

    @PutMapping("/{id}")
    public abstract ResponseEntity<ResponseDTO> atualizar(@PathVariable ID id, @Valid @RequestBody RequestDTO request);

    @DeleteMapping("/{id}")
    public abstract ResponseEntity<Void> deletar(@PathVariable ID id);
}

