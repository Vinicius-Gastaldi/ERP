package com.senac.gestao.services;

import com.senac.gestao.dtos.EnderecoRequest;
import com.senac.gestao.dtos.EnderecoResponse;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnderecoService {
    EnderecoResponse criar(EnderecoRequest request);
    EnderecoResponse atualizar(UUID id, EnderecoRequest request);
    EnderecoResponse buscarPorId(UUID id);
    Page<EnderecoResponse> listarPorPessoa(UUID pessoaId, Pageable pageable);
    void deletar(UUID id);
}
