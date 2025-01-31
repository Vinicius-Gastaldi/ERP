package com.senac.gestao.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

public record OrdemProducaoRequest(
        @NotBlank String numero,
        @Positive Integer quantidade,
        UUID produtoId,
        UUID linhaId,
        LocalDateTime dataInicio,
        LocalDateTime dataFim
) {}

