package com.senac.gestao.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record FornecedorRequest(
        @NotBlank String razaoSocial,
        String nomeFantasia,
        @NotBlank String documento,
        @Email String email,
        @Pattern(regexp = "\\d{10,11}") String telefone
) {}
