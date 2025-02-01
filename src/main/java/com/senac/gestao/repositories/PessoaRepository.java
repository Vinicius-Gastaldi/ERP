package com.senac.gestao.repositories;

import com.senac.gestao.models.Pessoa;
import org.hibernate.validator.constraints.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends BaseRepository<Pessoa, UUID> {
}
