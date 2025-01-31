package com.senac.gestao.dtos;

import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

public record ContatoResponse(
        UUID id,
        String tipo,
        String valor,
        String observacao,
        boolean principal,
        String horarioPermitido,
        LocalDateTime dataCriacao
) {}

