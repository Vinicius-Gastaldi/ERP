package com.senac.gestao.services;

import com.senac.gestao.dtos.FornecedorRequest;
import com.senac.gestao.dtos.FornecedorResponse;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FornecedorService {
    FornecedorResponse criar(FornecedorRequest request);
    FornecedorResponse atualizar(UUID id, FornecedorRequest request);
    FornecedorResponse buscarPorId(UUID id);
    Page<FornecedorResponse> listarTodos(Pageable pageable);
    void adicionarProduto(UUID fornecedorId, UUID produtoId);
    void removerProduto(UUID fornecedorId, UUID produtoId);
    void deletar(UUID id);
}
