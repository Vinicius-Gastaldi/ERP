package com.senac.gestao.dtos;

import java.util.UUID;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FornecedorResponse(
        UUID id,
        String razaoSocial,
        String nomeFantasia,
        String documento,
        String status,
        BigDecimal avaliacao,
        LocalDateTime dataCriacao
) {}