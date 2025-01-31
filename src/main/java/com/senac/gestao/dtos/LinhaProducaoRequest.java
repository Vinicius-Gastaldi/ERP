package com.senac.gestao.dtos;

import com.senac.gestao.models.enums.StatusLinhaProducaoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record LinhaProducaoRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        String descricao,

        @NotNull(message = "Status é obrigatório")
        StatusLinhaProducaoEnum status,

        @Positive(message = "Capacidade deve ser positiva")
        Integer capacidade,

        @Positive(message = "Eficiência deve ser positiva")
        BigDecimal eficiencia
) {}