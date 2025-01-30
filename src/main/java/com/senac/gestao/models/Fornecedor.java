package com.senac.gestao.models;

import com.senac.gestao.models.enums.StatusFornecedorEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fornecedores")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Fornecedor extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    private String razaoSocial;

    private String nomeFantasia;

    @NotBlank
    private String documento;

    @Enumerated(EnumType.STRING)
    private StatusFornecedorEnum status;

    @ManyToMany(mappedBy = "fornecedores")
    @Builder.Default
    private Set<Produto> produtos = new HashSet<>();
}
