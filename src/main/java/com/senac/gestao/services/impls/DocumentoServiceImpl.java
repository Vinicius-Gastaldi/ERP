package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.DocumentoRequest;
import com.senac.gestao.dtos.DocumentoResponse;
import com.senac.gestao.models.Documento;
import com.senac.gestao.repositories.DocumentoRepository;
import com.senac.gestao.repositories.PessoaRepository;
import com.senac.gestao.services.DocumentoService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final ModelMapper modelMapper;

    public DocumentoServiceImpl(DocumentoRepository documentoRepository, PessoaRepository pessoaRepository, ModelMapper modelMapper) {
        this.documentoRepository = documentoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public DocumentoResponse criar(DocumentoRequest request) {
        Documento documento = modelMapper.map(request, Documento.class);
        documento = documentoRepository.save(documento);
        return modelMapper.map(documento, DocumentoResponse.class);
    }

    @Override
    @Transactional
    public DocumentoResponse atualizar(UUID id, DocumentoRequest request) {
        Optional<Documento> documentoOptional = documentoRepository.findById(id);
        if (documentoOptional.isPresent()) {
            Documento documento = documentoOptional.get();
            modelMapper.map(request, documento);
            documento = documentoRepository.save(documento);
            return modelMapper.map(documento, DocumentoResponse.class);
        }
        throw new RuntimeException("Documento não encontrado com o ID: " + id);
    }

    @Override
    public DocumentoResponse buscarPorId(UUID id) {
        Optional<Documento> documentoOptional = documentoRepository.findById(id);
        if (documentoOptional.isPresent()) {
            return modelMapper.map(documentoOptional.get(), DocumentoResponse.class);
        }
        throw new RuntimeException("Documento não encontrado com o ID: " + id);
    }

    @Override
    public Page<DocumentoResponse> listarPorPessoa(UUID pessoaId, Pageable pageable) {
        Page<Documento> documentos = documentoRepository.findByPessoaId(pessoaId, pageable);
        return documentos.map(documento -> modelMapper.map(documento, DocumentoResponse.class));
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        documentoRepository.deleteById(id);
    }
}