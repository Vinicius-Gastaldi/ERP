package com.senac.gestao.dtos;

import com.senac.gestao.models.enums.TipoDocumentoEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record DocumentoRequest(
        TipoDocumentoEnum tipo,
        @NotBlank String numero,
        String orgaoEmissor,
        String uf,
        @Past LocalDate dataEmissao,
        @Future LocalDate dataValidade,
        boolean principal
) {}
