package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.EnderecoRequest;
import com.senac.gestao.dtos.EnderecoResponse;
import com.senac.gestao.models.Endereco;
import com.senac.gestao.repositories.EnderecoRepository;
import com.senac.gestao.services.EnderecoService;
import org.hibernate.validator.constraints.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final ModelMapper modelMapper;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, ModelMapper modelMapper) {
        this.enderecoRepository = enderecoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public EnderecoResponse criar(EnderecoRequest request) {
        // Valida o CEP
        request.validarCEP();

        Endereco endereco = modelMapper.map(request, Endereco.class);

        endereco = enderecoRepository.save(endereco);
        return modelMapper.map(endereco, EnderecoResponse.class);
    }

    @Override
    @Transactional
    public EnderecoResponse atualizar(UUID id, EnderecoRequest request) {
        // Valida o CEP
        request.validarCEP();

        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            modelMapper.map(request, endereco);



            endereco = enderecoRepository.save(endereco);
            return modelMapper.map(endereco, EnderecoResponse.class);
        }
        throw new RuntimeException("Endereço não encontrado com o ID: " + id);
    }

    @Override
    public EnderecoResponse buscarPorId(UUID id) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()) {
            return modelMapper.map(enderecoOptional.get(), EnderecoResponse.class);
        }
        throw new RuntimeException("Endereço não encontrado com o ID: " + id);
    }

    @Override
    public Page<EnderecoResponse> listarPorPessoa(UUID pessoaId, Pageable pageable) {
        Page<Endereco> enderecos = enderecoRepository.findByPessoaId(pessoaId);
        return enderecos.map(endereco -> modelMapper.map(endereco, EnderecoResponse.class));
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        enderecoRepository.deleteById(id);
    }
}