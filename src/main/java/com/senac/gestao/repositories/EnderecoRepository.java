package com.senac.gestao.repositories;

import com.senac.gestao.models.Endereco;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends BaseRepository<Endereco, UUID> {
    Page<Endereco> findByPessoaId(UUID pessoaId);
}
