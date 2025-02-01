package com.senac.gestao.dtos;

import java.util.UUID;

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
