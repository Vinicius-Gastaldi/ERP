package com.senac.gestao.repositories;

import com.senac.gestao.models.LinhaProducao;
import org.hibernate.validator.constraints.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface LinhaProducaoRepository extends BaseRepository<LinhaProducao, UUID> {
}
