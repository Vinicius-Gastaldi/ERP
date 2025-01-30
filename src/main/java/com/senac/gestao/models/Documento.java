package com.senac.gestao.models;

import com.senac.gestao.models.enums.TipoDocumentoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "documentos")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Documento extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TipoDocumentoEnum tipo;

    @NotBlank
    private String numero;

    private String orgaoEmissor;

    private String uf;

    private LocalDate dataEmissao;

    private LocalDate dataValidade;

    private boolean principal;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
