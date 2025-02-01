package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.LinhaProducaoRequest;
import com.senac.gestao.dtos.LinhaProducaoResponse;
import com.senac.gestao.models.LinhaProducao;
import com.senac.gestao.models.enums.StatusLinhaProducaoEnum;
import com.senac.gestao.repositories.LinhaProducaoRepository;
import com.senac.gestao.services.LinhaProducaoService;
import org.hibernate.validator.constraints.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LinhaProducaoServiceImpl implements LinhaProducaoService {

    private final LinhaProducaoRepository linhaProducaoRepository;

    private final ModelMapper modelMapper;

    public LinhaProducaoServiceImpl(LinhaProducaoRepository linhaProducaoRepository, ModelMapper modelMapper) {
        this.linhaProducaoRepository = linhaProducaoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public LinhaProducaoResponse criar(LinhaProducaoRequest request) {
        LinhaProducao linhaProducao = modelMapper.map(request, LinhaProducao.class);
        linhaProducao.setStatus(StatusLinhaProducaoEnum.DISPONIVEL); // Define o status inicial
        linhaProducao = linhaProducaoRepository.save(linhaProducao);
        return modelMapper.map(linhaProducao, LinhaProducaoResponse.class);
    }

    @Override
    @Transactional
    public LinhaProducaoResponse atualizar(UUID id, LinhaProducaoRequest request) {
        Optional<LinhaProducao> linhaOptional = linhaProducaoRepository.findById(id);
        if (linhaOptional.isPresent()) {
            LinhaProducao linhaProducao = linhaOptional.get();
            modelMapper.map(request, linhaProducao);
            linhaProducao = linhaProducaoRepository.save(linhaProducao);
            return modelMapper.map(linhaProducao, LinhaProducaoResponse.class);
        }
        throw new RuntimeException("Linha de produção não encontrada com o ID: " + id);
    }

    @Override
    public LinhaProducaoResponse buscarPorId(UUID id) {
        Optional<LinhaProducao> linhaOptional = linhaProducaoRepository.findById(id);
        if (linhaOptional.isPresent()) {
            return modelMapper.map(linhaOptional.get(), LinhaProducaoResponse.class);
        }
        throw new RuntimeException("Linha de produção não encontrada com o ID: " + id);
    }

    @Override
    public Page<LinhaProducaoResponse> listarTodos(Pageable pageable) {
        Page<LinhaProducao> linhas = linhaProducaoRepository.findAll(pageable);
        return linhas.map(linha -> modelMapper.map(linha, LinhaProducaoResponse.class));
    }

    @Override
    @Transactional
    public void iniciarProducao(UUID linhaId) {
        Optional<LinhaProducao> linhaOptional = linhaProducaoRepository.findById(linhaId);
        if (linhaOptional.isPresent()) {
            LinhaProducao linhaProducao = linhaOptional.get();
            if (linhaProducao.getStatus() == StatusLinhaProducaoEnum.DISPONIVEL) {
                linhaProducao.setStatus(StatusLinhaProducaoEnum.EM_OPERACAO);
                linhaProducaoRepository.save(linhaProducao);
            } else {
                throw new RuntimeException("A linha de produção não está disponível para iniciar produção.");
            }
        } else {
            throw new RuntimeException("Linha de produção não encontrada com o ID: " + linhaId);
        }
    }

    @Override
    @Transactional
    public void pararProducao(UUID linhaId) {
        Optional<LinhaProducao> linhaOptional = linhaProducaoRepository.findById(linhaId);
        if (linhaOptional.isPresent()) {
            LinhaProducao linhaProducao = linhaOptional.get();
            if (linhaProducao.getStatus() == StatusLinhaProducaoEnum.EM_OPERACAO) {
                linhaProducao.setStatus(StatusLinhaProducaoEnum.DISPONIVEL);
                linhaProducaoRepository.save(linhaProducao);
            } else {
                throw new RuntimeException("A linha de produção não está em produção para ser parada.");
            }
        } else {
            throw new RuntimeException("Linha de produção não encontrada com o ID: " + linhaId);
        }
    }

    @Override
    @Transactional
    public void agendarManutencao(UUID linhaId, LocalDateTime data) {
        Optional<LinhaProducao> linhaOptional = linhaProducaoRepository.findById(linhaId);
        if (linhaOptional.isPresent()) {
            LinhaProducao linhaProducao = linhaOptional.get();
            linhaProducao.setUltimaManutencao(data);
            linhaProducao.setStatus(StatusLinhaProducaoEnum.MANUTENCAO);
            linhaProducaoRepository.save(linhaProducao);
        } else {
            throw new RuntimeException("Linha de produção não encontrada com o ID: " + linhaId);
        }
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        linhaProducaoRepository.deleteById(id);
    }
}