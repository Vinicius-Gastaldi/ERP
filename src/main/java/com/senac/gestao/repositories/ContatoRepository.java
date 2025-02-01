package com.senac.gestao.repositories;

import com.senac.gestao.models.Contato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContatoRepository extends BaseRepository<Contato, UUID> {

    @Query("SELECT c FROM Contato c WHERE " +
            "c.pessoa.id = :pessoaId")
    Page<Contato> findByPessoaId(@Param("pessoaId") UUID pessoaId, Pageable pageable);
}