package com.senac.gestao.services;

import com.senac.gestao.dtos.*;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaService {
    PessoaResponse criar(PessoaRequest request);
    PessoaResponse atualizar(UUID id, PessoaRequest request);
    PessoaResponse buscarPorId(UUID id);
    Page<PessoaResponse> listarTodos(Pageable pageable);
    void adicionarEndereco(UUID pessoaId, EnderecoRequest endereco);
    void removerEndereco(UUID pessoaId, UUID enderecoId);
    void adicionarDocumento(UUID pessoaId, DocumentoRequest documento);
    void removerDocumento(UUID pessoaId, UUID documentoId);
    void adicionarContato(UUID pessoaId, ContatoRequest contato);
    void removerContato(UUID pessoaId, UUID contatoId);
    void deletar(UUID id);
}
