package com.senac.gestao.models;

import com.senac.gestao.models.enums.StatusOrdemEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordens_producao")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrdemProducao extends BaseEntity {

    @NotBlank
    private String numeroOrdem;

    @Enumerated(EnumType.STRING)
    private StatusOrdemEnum status;

    @Positive
    private Integer quantidade;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "linha_producao_id")
    private LinhaProducao linhaProducao;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}

