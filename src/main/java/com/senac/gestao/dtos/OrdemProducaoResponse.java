package com.senac.gestao.dtos;

import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrdemProducaoResponse(
        UUID id,
        String numero,
        String status,
        Integer quantidade,
        BigDecimal progresso,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        LocalDateTime dataCriacao
) {}
