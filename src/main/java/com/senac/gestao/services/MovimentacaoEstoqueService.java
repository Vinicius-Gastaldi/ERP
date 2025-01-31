package com.senac.gestao.services;

import com.senac.gestao.dtos.MovimentacaoRequest;
import com.senac.gestao.dtos.MovimentacaoResponse;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovimentacaoEstoqueService {
    MovimentacaoResponse criar(MovimentacaoRequest request);
    MovimentacaoResponse reverter(UUID movimentacaoId);
    MovimentacaoResponse buscarPorId(UUID id);
    Page<MovimentacaoResponse> listarPorEstoque(UUID estoqueId, Pageable pageable);
    Page<MovimentacaoResponse> listarPorProduto(UUID produtoId, Pageable pageable);
    void deletar(UUID id);
}
