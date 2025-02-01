package com.senac.gestao.dtos;

import java.util.UUID;

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
