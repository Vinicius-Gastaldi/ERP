package com.senac.gestao.models;

import com.senac.gestao.models.enums.TipoArmazemEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "estoques")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Estoque extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoArmazemEnum tipoArmazem;

    @Positive
    private BigDecimal capacidadeTotal;

    @PositiveOrZero
    private BigDecimal capacidadeUsada;

    @ManyToMany
    @JoinTable(
            name = "estoque_produto",
            joinColumns = @JoinColumn(name = "estoque_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    @Builder.Default
    private Set<Produto> produtos = new HashSet<>();

    @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
}