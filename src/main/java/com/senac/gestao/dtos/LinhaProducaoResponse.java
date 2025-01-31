// LinhaProducaoResponse.java
package com.senac.gestao.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record LinhaProducaoResponse(
        UUID id,
        String nome,
        String descricao,
        String status,
        Integer capacidade,
        BigDecimal eficiencia,
        LocalDateTime ultimaManutencao,
        List<OrdemProducaoResponse> ordensProducao,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}