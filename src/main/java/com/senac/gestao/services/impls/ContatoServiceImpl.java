package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.ContatoRequest;
import com.senac.gestao.dtos.ContatoResponse;
import com.senac.gestao.models.Contato;
import com.senac.gestao.repositories.ContatoRepository;
import com.senac.gestao.services.ContatoService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepository contatoRepository;

    private final ModelMapper modelMapper;

    public ContatoServiceImpl(ContatoRepository contatoRepository, ModelMapper modelMapper) {
        this.contatoRepository = contatoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ContatoResponse criar(ContatoRequest request) {
        Contato contato = modelMapper.map(request, Contato.class);
        contato = contatoRepository.save(contato);
        return modelMapper.map(contato, ContatoResponse.class);
    }

    @Override
    @Transactional
    public ContatoResponse atualizar(UUID id, ContatoRequest request) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if (contatoOptional.isPresent()) {
            Contato contato = contatoOptional.get();
            modelMapper.map(request, contato);
            contato = contatoRepository.save(contato);
            return modelMapper.map(contato, ContatoResponse.class);
        }
        throw new RuntimeException("Contato não encontrado com o ID: " + id);
    }

    @Override
    public ContatoResponse buscarPorId(UUID id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if (contatoOptional.isPresent()) {
            return modelMapper.map(contatoOptional.get(), ContatoResponse.class);
        }
        throw new RuntimeException("Contato não encontrado com o ID: " + id);
    }

    @Override
    public Page<ContatoResponse> listarPorPessoa(UUID pessoaId, Pageable pageable) {
        Page<Contato> contatos = contatoRepository.findByPessoaId(pessoaId, pageable);
        return contatos.map(contato -> modelMapper.map(contato, ContatoResponse.class));
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        contatoRepository.deleteById(id);
    }
}