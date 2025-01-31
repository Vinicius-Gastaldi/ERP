package com.senac.gestao.repositories;

import com.senac.gestao.models.Documento;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentoRepository extends BaseRepository<Documento, UUID> {

    Optional<Documento> findByNumero(String numero);

    @Query("SELECT d FROM Documento d WHERE " +
            "d.dataValidade >= CURRENT_DATE AND " +
            "d.principal = true AND " +
            "d.pessoa.id = :pessoaId")
    List<Documento> findDocumentosValidosPrincipais(
            @Param("pessoaId") UUID pessoaId
    );
}
