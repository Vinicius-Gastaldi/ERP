package com.senac.gestao.repositories;

import com.senac.gestao.models.Endereco;
import com.senac.gestao.models.enums.TipoEnderecoEnum;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends BaseRepository<Endereco, UUID> {

    @Query("SELECT e FROM Endereco e WHERE " +
            "e.pessoa.id = :pessoaId AND " +
            "e.tipo = :tipo")
    List<Endereco> findByPessoaETipo(
            @Param("pessoaId") UUID pessoaId,
            @Param("tipo") TipoEnderecoEnum tipo
    );

    @Query("SELECT e FROM Endereco e WHERE " +
            "e.cep = :cep AND " +
            "e.principal = true")
    List<Endereco> findPrincipaisPorCep(@Param("cep") String cep);
}
