package com.senac.gestao.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PessoaRequest(
        @NotBlank @Size(min = 2, max = 100) String nome,
        @NotBlank @Size(min = 2, max = 100) String sobrenome,
        @Pattern(regexp = "\\d{11}") String documento,
        List<EnderecoRequest> enderecos,
        List<DocumentoRequest> documentos,
        List<ContatoRequest> contatos
) {}
