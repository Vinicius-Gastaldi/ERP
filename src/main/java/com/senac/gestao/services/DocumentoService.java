package com.senac.gestao.services;

import com.senac.gestao.dtos.DocumentoRequest;
import com.senac.gestao.dtos.DocumentoResponse;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentoService {
    DocumentoResponse criar(DocumentoRequest request);
    DocumentoResponse atualizar(UUID id, DocumentoRequest request);
    DocumentoResponse buscarPorId(UUID id);
    Page<DocumentoResponse> listarPorPessoa(UUID pessoaId, Pageable pageable);
    void tornarPrincipal(UUID documentoId);
    void deletar(UUID id);
}
