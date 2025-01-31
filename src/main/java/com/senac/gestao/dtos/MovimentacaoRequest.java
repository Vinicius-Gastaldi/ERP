package com.senac.gestao.dtos;

import com.senac.gestao.models.enums.TipoMovimentacaoEnum;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;

public record MovimentacaoRequest(
        TipoMovimentacaoEnum tipo,
        @Positive Integer quantidade,
        UUID produtoId,
        UUID estoqueId,
        UUID responsavelId,
        String descricao
) {}
