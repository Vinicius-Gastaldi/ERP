package com.senac.gestao.models;

import com.senac.gestao.models.enums.TipoContatoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Entity
@Table(name = "contatos")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Contato extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TipoContatoEnum tipo;

    @NotBlank
    private String valor;

    private String observacao;

    private boolean principal;

    private LocalTime horarioInicioPermitido;

    private LocalTime horarioFimPermitido;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
