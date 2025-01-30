package com.senac.gestao.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;

@Entity
@Table(name = "linhas_producao")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LinhaProducao extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusLinhaProducaoEnum status;

    @Positive
    private Integer capacidade;

    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private BigDecimal eficiencia;

    @OneToMany(mappedBy = "linhaProducao")
    @Builder.Default
    private List<OrdemProducao> ordens = new ArrayList<>();

    public void atualizarEficiencia() {
        this.eficiencia = calcularEficiencia();
    }

    private BigDecimal calcularEficiencia() {
        // Lógica complexa de cálculo
        return BigDecimal.valueOf(0.85);
    }
}