package com.senac.gestao.dtos;

import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DocumentoResponse(
        UUID id,
        String tipo,
        String numero,
        String orgaoEmissor,
        String uf,
        LocalDate dataEmissao,
        LocalDate dataValidade,
        boolean principal,
        LocalDateTime dataCriacao
) {}
