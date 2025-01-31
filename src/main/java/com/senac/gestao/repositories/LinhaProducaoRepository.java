package com.senac.gestao.repositories;

import com.senac.gestao.models.LinhaProducao;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LinhaProducaoRepository extends BaseRepository<LinhaProducao, UUID> {

    @Query("SELECT l FROM LinhaProducao l WHERE " +
            "l.status = 'DISPONIVEL' AND " +
            "l.capacidade >= :quantidade")
    List<LinhaProducao> findDisponiveisComCapacidade(
            @Param("quantidade") Integer quantidade
    );

    @Query("SELECT l FROM LinhaProducao l WHERE " +
            "l.ultimaManutencao < :dataLimite")
    List<LinhaProducao> findParaManutencao(
            @Param("dataLimite") LocalDateTime dataLimite
    );
}
