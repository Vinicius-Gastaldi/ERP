package com.senac.gestao.dtos;

import com.senac.gestao.models.enums.TipoContatoEnum;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;

public record ContatoRequest(
        TipoContatoEnum tipo,
        @NotBlank String valor,
        String observacao,
        boolean principal,
        LocalTime horarioInicio,
        LocalTime horarioFim
) {}
