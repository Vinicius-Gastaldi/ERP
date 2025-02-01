package com.senac.gestao.services;

import com.senac.gestao.dtos.LinhaProducaoRequest;
import com.senac.gestao.dtos.LinhaProducaoResponse;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface LinhaProducaoService {
    LinhaProducaoResponse criar(LinhaProducaoRequest request);
    LinhaProducaoResponse atualizar(UUID id, LinhaProducaoRequest request);
    LinhaProducaoResponse buscarPorId(UUID id);
    Page<LinhaProducaoResponse> listarTodos(Pageable pageable);
    void iniciarProducao(UUID linhaId);
    void pararProducao(UUID linhaId);
    void agendarManutencao(UUID linhaId, LocalDateTime data);
    void deletar(UUID id);
}