package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.OrdemProducaoRequest;
import com.senac.gestao.dtos.OrdemProducaoResponse;
import com.senac.gestao.models.OrdemProducao;
import com.senac.gestao.models.enums.StatusOrdemEnum;
import com.senac.gestao.repositories.OrdemProducaoRepository;
import com.senac.gestao.services.OrdemProducaoService;
import org.hibernate.validator.constraints.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrdemProducaoServiceImpl implements OrdemProducaoService {

    private final OrdemProducaoRepository ordemProducaoRepository;

    private final ModelMapper modelMapper;

    public OrdemProducaoServiceImpl(OrdemProducaoRepository ordemProducaoRepository, ModelMapper modelMapper) {
        this.ordemProducaoRepository = ordemProducaoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public OrdemProducaoResponse criar(OrdemProducaoRequest request) {
        OrdemProducao ordemProducao = modelMapper.map(request, OrdemProducao.class);
        ordemProducao.setStatus(StatusOrdemEnum.EM_ANDAMENTO); // Define o status inicial
        ordemProducao = ordemProducaoRepository.save(ordemProducao);
        return modelMapper.map(ordemProducao, OrdemProducaoResponse.class);
    }

    @Override
    @Transactional
    public OrdemProducaoResponse atualizar(UUID id, OrdemProducaoRequest request) {
        Optional<OrdemProducao> ordemOptional = ordemProducaoRepository.findById(id);
        if (ordemOptional.isPresent()) {
            OrdemProducao ordemProducao = ordemOptional.get();
            modelMapper.map(request, ordemProducao);
            ordemProducao = ordemProducaoRepository.save(ordemProducao);
            return modelMapper.map(ordemProducao, OrdemProducaoResponse.class);
        }
        throw new RuntimeException("Ordem de produção não encontrada com o ID: " + id);
    }

    @Override
    public OrdemProducaoResponse buscarPorId(UUID id) {
        Optional<OrdemProducao> ordemOptional = ordemProducaoRepository.findById(id);
        if (ordemOptional.isPresent()) {
            return modelMapper.map(ordemOptional.get(), OrdemProducaoResponse.class);
        }
        throw new RuntimeException("Ordem de produção não encontrada com o ID: " + id);
    }

    @Override
    public Page<OrdemProducaoResponse> listarTodos(Pageable pageable) {
        Page<OrdemProducao> ordens = ordemProducaoRepository.findAll(pageable);
        return ordens.map(ordem -> modelMapper.map(ordem, OrdemProducaoResponse.class));
    }

    @Override
    @Transactional
    public void cancelarOrdem(UUID ordemId) {
        Optional<OrdemProducao> ordemOptional = ordemProducaoRepository.findById(ordemId);
        if (ordemOptional.isPresent()) {
            OrdemProducao ordemProducao = ordemOptional.get();
            ordemProducao.setStatus(StatusOrdemEnum.CANCELADA);
            ordemProducaoRepository.save(ordemProducao);
        } else {
            throw new RuntimeException("Ordem de produção não encontrada com o ID: " + ordemId);
        }
    }

    @Override
    @Transactional
    public void concluirOrdem(UUID ordemId) {
        Optional<OrdemProducao> ordemOptional = ordemProducaoRepository.findById(ordemId);
        if (ordemOptional.isPresent()) {
            OrdemProducao ordemProducao = ordemOptional.get();
            ordemProducao.setStatus(StatusOrdemEnum.CONCLUIDA);
            ordemProducao.setDataFim(LocalDateTime.now());
            ordemProducaoRepository.save(ordemProducao);
        } else {
            throw new RuntimeException("Ordem de produção não encontrada com o ID: " + ordemId);
        }
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        ordemProducaoRepository.deleteById(id);
    }
}