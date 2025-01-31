package com.senac.gestao.services;

import com.senac.gestao.dtos.FornecedorRequest;
import com.senac.gestao.dtos.FornecedorResponse;
import com.senac.gestao.models.enums.StatusFornecedorEnum;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface FornecedorService {
    FornecedorResponse criar(FornecedorRequest request);
    FornecedorResponse atualizar(UUID id, FornecedorRequest request);
    FornecedorResponse buscarPorId(UUID id);
    Page<FornecedorResponse> listarTodos(Pageable pageable);
    void avaliarFornecedor(UUID fornecedorId, BigDecimal nota);
    void alterarStatus(UUID fornecedorId, StatusFornecedorEnum novoStatus);
    void adicionarProduto(UUID fornecedorId, UUID produtoId);
    void removerProduto(UUID fornecedorId, UUID produtoId);
    void deletar(UUID id);
}
