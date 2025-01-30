package com.senac.gestao.models;

import com.senac.gestao.models.enums.CategoriaProdutoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "produtos")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Produto extends BaseEntity {

    @NotBlank
    private String sku;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    private CategoriaProdutoEnum categoria;

    @Positive
    private BigDecimal precoVenda;

    @ManyToMany(mappedBy = "produtos")
    @Builder.Default
    private Set<Estoque> estoques = new HashSet<>();

    @ManyToMany(mappedBy = "produtos")
    @Builder.Default
    private Set<Fornecedor> fornecedores = new HashSet<>();
}
