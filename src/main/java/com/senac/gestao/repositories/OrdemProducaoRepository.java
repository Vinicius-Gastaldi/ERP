package com.senac.gestao.repositories;

import com.senac.gestao.models.OrdemProducao;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdemProducaoRepository extends BaseRepository<OrdemProducao, UUID> {

    @Query("SELECT o FROM OrdemProducao o WHERE " +
            "o.status = 'EM_ANDAMENTO' AND " +
            "o.linhaProducao.id = :linhaId")
    List<OrdemProducao> findAtivasPorLinha(
            @Param("linhaId") UUID linhaId
    );

    @Query("SELECT o FROM OrdemProducao o WHERE " +
            "o.produto.id = :produtoId AND " +
            "o.dataFim BETWEEN :inicio AND :fim")
    Page<OrdemProducao> findConcluidasPorProdutoEPeriodo(
            @Param("produtoId") UUID produtoId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim,
            Pageable pageable
    );
}

