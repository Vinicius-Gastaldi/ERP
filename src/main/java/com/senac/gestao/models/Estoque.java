package com.senac.gestao.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;

@Entity
@Table(name = "estoques")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Estoque extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoArmazemEnum tipoArmazem;

    @Positive
    private BigDecimal capacidadeTotal;

    @PositiveOrZero
    private BigDecimal capacidadeUsada;

    @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "estoque_produto",
            joinColumns = @JoinColumn(name = "estoque_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    @Builder.Default
    private Set<Produto> produtos = new HashSet<>();

    public BigDecimal calcularOcupacao() {
        return capacidadeUsada.divide(capacidadeTotal, 2, RoundingMode.HALF_UP);
    }
}