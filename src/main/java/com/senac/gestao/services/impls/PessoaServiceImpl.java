package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.*;
import com.senac.gestao.models.Contato;
import com.senac.gestao.models.Documento;
import com.senac.gestao.models.Endereco;
import com.senac.gestao.models.Pessoa;
import com.senac.gestao.repositories.PessoaRepository;
import com.senac.gestao.services.PessoaService;
import org.hibernate.validator.constraints.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository, ModelMapper modelMapper) {
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PessoaResponse criar(PessoaRequest request) {
        Pessoa pessoa = modelMapper.map(request, Pessoa.class);
        pessoa = pessoaRepository.save(pessoa);
        return modelMapper.map(pessoa, PessoaResponse.class);
    }

    @Override
    public PessoaResponse atualizar(UUID id, PessoaRequest request) {
        return pessoaRepository.findById(id).map(pessoa -> {
            modelMapper.map(request, pessoa);
            pessoa = pessoaRepository.save(pessoa);
            return modelMapper.map(pessoa, PessoaResponse.class);
        }).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    @Override
    public PessoaResponse buscarPorId(UUID id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        return modelMapper.map(pessoa, PessoaResponse.class);
    }

    @Override
    public Page<PessoaResponse> listarTodos(Pageable pageable) {
        return pessoaRepository.findAll(pageable).map(pessoa -> modelMapper.map(pessoa, PessoaResponse.class));
    }

    @Override
    public void adicionarEndereco(UUID pessoaId, EnderecoRequest endereco) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.getEnderecos().add(modelMapper.map(endereco, Endereco.class));
        pessoaRepository.save(pessoa);
    }

    @Override
    public void removerEndereco(UUID pessoaId, UUID enderecoId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.getEnderecos().removeIf(endereco -> endereco.getId().equals(enderecoId));
        pessoaRepository.save(pessoa);
    }

    @Override
    public void adicionarDocumento(UUID pessoaId, DocumentoRequest documento) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.getDocumentos().add(modelMapper.map(documento, Documento.class));
        pessoaRepository.save(pessoa);
    }

    @Override
    public void removerDocumento(UUID pessoaId, UUID documentoId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.getDocumentos().removeIf(documento -> documento.getId().equals(documentoId));
        pessoaRepository.save(pessoa);
    }

    @Override
    public void adicionarContato(UUID pessoaId, ContatoRequest contato) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.getContatos().add(modelMapper.map(contato, Contato.class));
        pessoaRepository.save(pessoa);
    }

    @Override
    public void removerContato(UUID pessoaId, UUID contatoId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.getContatos().removeIf(contato -> contato.getId().equals(contatoId));
        pessoaRepository.save(pessoa);
    }

    @Override
    public void deletar(UUID id) {
        if (!pessoaRepository.existsById(id)) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        pessoaRepository.deleteById(id);
    }
}
