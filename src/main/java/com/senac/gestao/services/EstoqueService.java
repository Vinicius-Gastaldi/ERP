package com.senac.gestao.services;

import com.senac.gestao.dtos.EstoqueRequest;
import com.senac.gestao.dtos.EstoqueResponse;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstoqueService {
    EstoqueResponse criar(EstoqueRequest request);
    EstoqueResponse atualizar(UUID id, EstoqueRequest request);
    EstoqueResponse buscarPorId(UUID id);
    Page<EstoqueResponse> listarTodos(Pageable pageable);
    void adicionarProduto(UUID estoqueId, UUID produtoId);
    void removerProduto(UUID estoqueId, UUID produtoId);
    void deletar(UUID id);
}
