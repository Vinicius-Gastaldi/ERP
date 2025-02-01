package com.senac.gestao.services;

import com.senac.gestao.dtos.OrdemProducaoRequest;
import com.senac.gestao.dtos.OrdemProducaoResponse;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdemProducaoService {
    OrdemProducaoResponse criar(OrdemProducaoRequest request);
    OrdemProducaoResponse atualizar(UUID id, OrdemProducaoRequest request);
    OrdemProducaoResponse buscarPorId(UUID id);
    Page<OrdemProducaoResponse> listarTodos(Pageable pageable);
    void cancelarOrdem(UUID ordemId);
    void concluirOrdem(UUID ordemId);
    void deletar(UUID id);
}
