package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.AuditoriaResponse;
import com.senac.gestao.models.Auditoria;
import com.senac.gestao.models.enums.AcaoAuditoria;
import com.senac.gestao.repositories.AuditoriaRepository;
import com.senac.gestao.services.AuditoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final ModelMapper modelMapper;

    public AuditoriaServiceImpl(AuditoriaRepository auditoriaRepository, ModelMapper modelMapper) {
        this.auditoriaRepository = auditoriaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<AuditoriaResponse> listarAuditorias(Pageable pageable) {
        return auditoriaRepository.findAll(pageable)
                .map(auditoria -> modelMapper.map(auditoria, AuditoriaResponse.class));
    }

    @Override
    public Page<AuditoriaResponse> filtrarPorEntidade(String entidade, Pageable pageable) {
        return auditoriaRepository.filtrarPorEntidadeEPeriodo(entidade, LocalDateTime.MIN, LocalDateTime.MAX, pageable)
                .map(auditoria -> modelMapper.map(auditoria, AuditoriaResponse.class));
    }

    @Override
    public Page<AuditoriaResponse> filtrarPorPeriodo(LocalDateTime inicio, LocalDateTime fim, Pageable pageable) {
        return auditoriaRepository.filtrarPorEntidadeEPeriodo("%", inicio, fim, pageable)
                .map(auditoria -> modelMapper.map(auditoria, AuditoriaResponse.class));
    }

    @Override
    @Transactional
    public void registrarAcao(String entidade, AcaoAuditoria acao, String detalhes) {
        Auditoria auditoria = Auditoria.builder()
                .acao(acao)
                .entidade(entidade)
                .dadosNovos(detalhes)
                .dataCriacao(LocalDateTime.now())
                .build();
        auditoriaRepository.save(auditoria);
    }
}
