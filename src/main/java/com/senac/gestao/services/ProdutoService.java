package com.senac.gestao.services;

import com.senac.gestao.dtos.ProdutoRequest;
import com.senac.gestao.dtos.ProdutoResponse;
import com.senac.gestao.models.enums.CategoriaProdutoEnum;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoService {
    ProdutoResponse criar(ProdutoRequest request);
    ProdutoResponse atualizar(UUID id, ProdutoRequest request);
    ProdutoResponse buscarPorId(UUID id);
    Page<ProdutoResponse> listarTodos(Pageable pageable);
    Page<ProdutoResponse> listarPorCategoria(CategoriaProdutoEnum categoria, Pageable pageable);
    List<ProdutoResponse> listarParaReposicao();
    List<ProdutoResponse> listarExcedentes();
    void ajustarEstoque(UUID produtoId, Integer quantidade);
    void deletar(UUID id);
}
