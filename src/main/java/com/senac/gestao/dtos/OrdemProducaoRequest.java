package com.senac.gestao.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

import java.time.LocalDateTime;

public record OrdemProducaoRequest(
        @NotBlank String numero,
        @Positive Integer quantidade,
        UUID produtoId,
        UUID linhaId,
        LocalDateTime dataInicio,
        LocalDateTime dataFim
) {}

