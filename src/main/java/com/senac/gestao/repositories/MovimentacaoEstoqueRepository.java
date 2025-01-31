package com.senac.gestao.repositories;

import com.senac.gestao.models.MovimentacaoEstoque;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentacaoEstoqueRepository extends BaseRepository<MovimentacaoEstoque, UUID> {

    @Query("SELECT m FROM MovimentacaoEstoque m WHERE " +
            "m.estoque.id = :estoqueId AND " +
            "m.dataCriacao BETWEEN :inicio AND :fim")
    Page<MovimentacaoEstoque> findPorEstoqueEPeriodo(
            @Param("estoqueId") UUID estoqueId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim,
            Pageable pageable
    );

    @Query("SELECT m FROM MovimentacaoEstoque m WHERE " +
            "m.tipo = 'ENTRADA' AND " +
            "m.produto.id = :produtoId")
    List<MovimentacaoEstoque> findEntradasPorProduto(
            @Param("produtoId") UUID produtoId
    );
}
