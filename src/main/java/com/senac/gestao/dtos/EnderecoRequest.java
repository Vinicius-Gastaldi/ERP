package com.senac.gestao.dtos;

import com.senac.gestao.models.enums.TipoEnderecoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(
        @NotBlank String logradouro,
        @NotBlank String numero,
        String complemento,
        @NotBlank String bairro,
        @NotBlank String cidade,
        @NotBlank @Size(min = 2, max = 2) String estado,
        @Pattern(regexp = "\\d{5}-\\d{3}") String cep,
        TipoEnderecoEnum tipo,
        boolean principal
) {
    public void validarCEP() {
        if (!cep.matches("\\d{5}-\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido");
        }
    }
}

