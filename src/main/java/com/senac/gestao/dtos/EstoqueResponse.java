package com.senac.gestao.dtos;

import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EstoqueResponse(
        UUID id,
        String nome,
        String descricao,
        String tipo,
        BigDecimal capacidadeTotal,
        BigDecimal capacidadeUsada,
        BigDecimal taxaOcupacao,
        LocalDateTime dataCriacao
) {}
