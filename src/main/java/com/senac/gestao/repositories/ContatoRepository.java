package com.senac.gestao.repositories;

import com.senac.gestao.models.Contato;
import com.senac.gestao.models.enums.TipoContatoEnum;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends BaseRepository<Contato, UUID> {

    List<Contato> findByPessoaId(UUID pessoaId);

    @Query("SELECT c FROM Contato c WHERE " +
            "c.tipo = :tipo AND " +
            "c.pessoa.id = :pessoaId")
    List<Contato> findByTipoEPessoa(
            @Param("tipo") TipoContatoEnum tipo,
            @Param("pessoaId") UUID pessoaId
    );
}
