package com.senac.gestao.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ProdutoResponse(
        UUID id,
        String sku,
        String nome,
        String categoria,
        BigDecimal precoVenda,
        List<EstoqueResponse> estoques,
        List<FornecedorResponse> fornecedores,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}