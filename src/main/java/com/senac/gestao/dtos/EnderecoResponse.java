package com.senac.gestao.dtos;

import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

public record EnderecoResponse(
        UUID id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String tipo,
        boolean principal,
        LocalDateTime dataCriacao
) {}
