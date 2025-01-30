package com.senac.gestao.models;

import com.senac.gestao.models.enums.StatusLinhaProducaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "linhas_producao")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LinhaProducao extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusLinhaProducaoEnum status;

    @Positive
    private Integer capacidade;

    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private BigDecimal eficiencia;

    private LocalDateTime ultimaManutencao;

    @OneToMany(mappedBy = "linhaProducao")
    @Builder.Default
    private List<OrdemProducao> ordensProducao = new ArrayList<>();
}