package com.senac.gestao.models;

import com.senac.gestao.models.enums.TipoEnderecoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "enderecos")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Endereco extends BaseEntity {

    @NotBlank(message = "Logradouro é obrigatório")
    @Size(max = 255, message = "Logradouro deve ter até 255 caracteres")
    @Column(nullable = false)
    private String logradouro;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 20, message = "Número deve ter até 20 caracteres")
    @Column(nullable = false)
    private String numero;

    @Size(max = 100, message = "Complemento deve ter até 100 caracteres")
    private String complemento;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 100, message = "Bairro deve ter até 100 caracteres")
    @Column(nullable = false)
    private String bairro;

    @NotBlank(message = "Cidade é obrigatório")
    @Size(max = 100, message = "Cidade deve ter até 100 caracteres")
    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
    @Column(nullable = false, length = 2)
    private String estado;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(
            regexp = "^[0-9]{5}-[0-9]{3}$",
            message = "CEP inválido. Formato esperado: 00000-000"
    )
    @Column(nullable = false, length = 9)
    private String cep;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoEnderecoEnum tipo;

    @Column(nullable = false)
    private Boolean principal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    @ToString.Exclude
    private Pessoa pessoa;
}