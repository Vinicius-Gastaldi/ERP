package com.senac.gestao.controllers;

import com.senac.gestao.dtos.AuditoriaResponse;
import com.senac.gestao.models.enums.AcaoAuditoria;
import com.senac.gestao.services.AuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auditorias")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @GetMapping
    public ResponseEntity<Page<AuditoriaResponse>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(auditoriaService.listarAuditorias(pageable));
    }

    @GetMapping("/filtrar-por-entidade")
    public ResponseEntity<Page<AuditoriaResponse>> filtrarPorEntidade(
            @RequestParam String entidade, Pageable pageable) {
        return ResponseEntity.ok(auditoriaService.filtrarPorEntidade(entidade, pageable));
    }

    @GetMapping("/filtrar-por-periodo")
    public ResponseEntity<Page<AuditoriaResponse>> filtrarPorPeriodo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim,
            Pageable pageable) {
        return ResponseEntity.ok(auditoriaService.filtrarPorPeriodo(inicio, fim, pageable));
    }

    @PostMapping("/registrar-acao")
    public ResponseEntity<Void> registrarAcao(
            @RequestParam String entidade,
            @RequestParam AcaoAuditoria acao,
            @RequestParam String detalhes) {
        auditoriaService.registrarAcao(entidade, acao, detalhes);
        return ResponseEntity.noContent().build();
    }
}