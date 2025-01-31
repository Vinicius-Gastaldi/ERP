package com.senac.gestao.dtos;

import com.senac.gestao.models.enums.CategoriaProdutoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "SKU é obrigatório")
        String sku,

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "Categoria é obrigatória")
        CategoriaProdutoEnum categoria,

        @Positive(message = "Preço de venda deve ser positivo")
        BigDecimal precoVenda
) {}