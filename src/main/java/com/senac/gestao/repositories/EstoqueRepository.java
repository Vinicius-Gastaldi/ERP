package com.senac.gestao.repositories;

import com.senac.gestao.models.Estoque;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EstoqueRepository extends BaseRepository<Estoque, UUID> {

    @Query("SELECT e FROM Estoque e WHERE " +
            "e.capacidadeTotal - e.capacidadeUsada >= :quantidade")
    List<Estoque> findComCapacidadePara(
            @Param("quantidade") BigDecimal quantidade
    );

    @Query("SELECT e FROM Estoque e JOIN e.produtos p WHERE p.id = :produtoId")
    List<Estoque> findByProdutoId(@Param("produtoId") UUID produtoId);
}

