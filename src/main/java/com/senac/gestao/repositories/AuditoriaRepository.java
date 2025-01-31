package com.senac.gestao.repositories;

import com.senac.gestao.models.Auditoria;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AuditoriaRepository extends BaseRepository<Auditoria, UUID> {

    @Query("SELECT a FROM Auditoria a WHERE " +
            "a.entidade = :entidade AND " +
            "a.dataCriacao BETWEEN :inicio AND :fim")
    Page<Auditoria> filtrarPorEntidadeEPeriodo(
            @Param("entidade") String entidade,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim,
            Pageable pageable
    );
}
