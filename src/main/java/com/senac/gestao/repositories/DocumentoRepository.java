package com.senac.gestao.repositories;

import com.senac.gestao.models.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, UUID> {
    Page<Documento> findByPessoaId(UUID pessoaId, Pageable pageable);

    Optional<Documento> findById(UUID id);
}