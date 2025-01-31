package com.senac.gestao.repositories;

import com.senac.gestao.models.Pessoa;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends BaseRepository<Pessoa, UUID> {

    @Query("SELECT p FROM Pessoa p WHERE " +
            "LOWER(CONCAT(p.nome, ' ', p.sobrenome)) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Pessoa> searchByNomeCompleto(
            @Param("nome") String nome,
            Pageable pageable
    );

    @Query("SELECT p FROM Pessoa p JOIN p.documentos d WHERE " +
            "d.tipo = 'CPF' AND " +
            "d.numero = :cpf")
    Optional<Pessoa> findByCpf(@Param("cpf") String cpf);
}
