package com.senac.gestao.dtos;

import com.senac.gestao.models.enums.TipoArmazemEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record EstoqueRequest(
        @NotBlank String nome,
        String descricao,
        TipoArmazemEnum tipo,
        @Positive BigDecimal capacidadeTotal,
        @PositiveOrZero BigDecimal capacidadeUsada
) {}
