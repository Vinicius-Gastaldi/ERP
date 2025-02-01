package com.senac.gestao.repositories;

import com.senac.gestao.models.OrdemProducao;
import org.hibernate.validator.constraints.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemProducaoRepository extends BaseRepository<OrdemProducao, UUID> {
}

