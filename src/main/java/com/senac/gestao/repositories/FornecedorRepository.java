package com.senac.gestao.repositories;

import com.senac.gestao.models.Fornecedor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends BaseRepository<Fornecedor, UUID> {

    @Query("SELECT f FROM Fornecedor f WHERE " +
            "f.status = 'ATIVO' AND " +
            "f.documento = :documento")
    Optional<Fornecedor> findAtivoPorDocumento(
            @Param("documento") String documento
    );

    @Query("SELECT f FROM Fornecedor f JOIN f.produtos p WHERE p.id = :produtoId")
    Page<Fornecedor> findFornecedoresPorProduto(
            @Param("produtoId") UUID produtoId,
            Pageable pageable
    );
}
