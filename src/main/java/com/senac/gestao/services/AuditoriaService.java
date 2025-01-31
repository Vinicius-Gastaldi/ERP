package com.senac.gestao.services;

import com.senac.gestao.dtos.AuditoriaResponse;
import com.senac.gestao.models.enums.AcaoAuditoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface AuditoriaService {
    Page<AuditoriaResponse> listarAuditorias(Pageable pageable);
    Page<AuditoriaResponse> filtrarPorEntidade(String entidade, Pageable pageable);
    Page<AuditoriaResponse> filtrarPorPeriodo(LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
    void registrarAcao(String entidade, AcaoAuditoria acao, String detalhes);
}
