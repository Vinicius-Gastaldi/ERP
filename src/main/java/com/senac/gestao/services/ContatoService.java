package com.senac.gestao.services;

import com.senac.gestao.dtos.ContatoRequest;
import com.senac.gestao.dtos.ContatoResponse;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContatoService {
    ContatoResponse criar(ContatoRequest request);
    ContatoResponse atualizar(UUID id, ContatoRequest request);
    ContatoResponse buscarPorId(UUID id);
    Page<ContatoResponse> listarPorPessoa(UUID pessoaId, Pageable pageable);
    void tornarPrincipal(UUID contatoId);
    void deletar(UUID id);
}

