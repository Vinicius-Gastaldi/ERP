package com.senac.gestao.dtos;

import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

public record MovimentacaoResponse(
        UUID id,
        String tipo,
        Integer quantidade,
        UUID produtoId,
        UUID estoqueId,
        UUID responsavelId,
        String descricao,
        LocalDateTime dataMovimentacao
) {}
