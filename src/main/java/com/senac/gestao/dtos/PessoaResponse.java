package com.senac.gestao.dtos;

import java.util.UUID;

import java.time.LocalDateTime;
import java.util.List;

public record PessoaResponse(
        UUID id,
        String nome,
        String sobrenome,
        String documento,
        List<EnderecoResponse> enderecos,
        List<DocumentoResponse> documentos,
        List<ContatoResponse> contatos,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {}
