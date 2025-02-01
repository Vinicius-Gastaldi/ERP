package com.senac.gestao.dtos;

import java.util.UUID;

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

