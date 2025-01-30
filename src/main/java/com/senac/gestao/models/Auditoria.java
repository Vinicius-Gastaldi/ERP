package com.senac.gestao.models;

import com.senac.gestao.models.enums.AcaoAuditoria;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "auditorias")
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Auditoria extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private AcaoAuditoria acao;

    private String entidade;

    @Column(columnDefinition = "TEXT")
    private String dadosAntigos;

    @Column(columnDefinition = "TEXT")
    private String dadosNovos;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Pessoa responsavel;
}
