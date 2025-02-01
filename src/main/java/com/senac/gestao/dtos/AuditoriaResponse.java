package com.senac.gestao.dtos;

import java.util.UUID;

import java.time.LocalDateTime;

public record AuditoriaResponse(
        UUID id,
        String acao,
        String entidade,
        String dadosAntigos,
        String dadosNovos,
        UUID usuarioId,
        LocalDateTime dataCriacao
) {}
