package com.senac.gestao.models;

import com.senac.gestao.models.enums.TipoMovimentacaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "movimentacoes_estoque")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovimentacaoEstoque extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TipoMovimentacaoEnum tipo;

    @Positive
    private Integer quantidade;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Pessoa responsavel;
}
