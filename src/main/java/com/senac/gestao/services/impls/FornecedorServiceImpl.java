package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.FornecedorRequest;
import com.senac.gestao.dtos.FornecedorResponse;
import com.senac.gestao.models.Fornecedor;
import com.senac.gestao.models.Produto;
import com.senac.gestao.models.enums.StatusFornecedorEnum;
import com.senac.gestao.repositories.FornecedorRepository;
import com.senac.gestao.repositories.ProdutoRepository;
import com.senac.gestao.services.FornecedorService;
import org.hibernate.validator.constraints.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    private final  ProdutoRepository produtoRepository;

    private final ModelMapper modelMapper;

    public FornecedorServiceImpl(FornecedorRepository fornecedorRepository, ProdutoRepository produtoRepository, ModelMapper modelMapper) {
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public FornecedorResponse criar(FornecedorRequest request) {
        Fornecedor fornecedor = modelMapper.map(request, Fornecedor.class);
        fornecedor.setStatus(StatusFornecedorEnum.ATIVO); // Define o status inicial
        fornecedor = fornecedorRepository.save(fornecedor);
        return modelMapper.map(fornecedor, FornecedorResponse.class);
    }

    @Override
    @Transactional
    public FornecedorResponse atualizar(UUID id, FornecedorRequest request) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
        if (fornecedorOptional.isPresent()) {
            Fornecedor fornecedor = fornecedorOptional.get();
            modelMapper.map(request, fornecedor);
            fornecedor = fornecedorRepository.save(fornecedor);
            return modelMapper.map(fornecedor, FornecedorResponse.class);
        }
        throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
    }

    @Override
    public FornecedorResponse buscarPorId(UUID id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
        if (fornecedorOptional.isPresent()) {
            return modelMapper.map(fornecedorOptional.get(), FornecedorResponse.class);
        }
        throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
    }

    @Override
    public Page<FornecedorResponse> listarTodos(Pageable pageable) {
        Page<Fornecedor> fornecedores = fornecedorRepository.findAll(pageable);
        return fornecedores.map(fornecedor -> modelMapper.map(fornecedor, FornecedorResponse.class));
    }

    @Override
    @Transactional
    public void adicionarProduto(UUID fornecedorId, UUID produtoId) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(fornecedorId);
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);

        if (fornecedorOptional.isPresent() && produtoOptional.isPresent()) {
            Fornecedor fornecedor = fornecedorOptional.get();
            Produto produto = produtoOptional.get();

            fornecedor.getProdutos().add(produto);
            produto.getFornecedores().add(fornecedor);

            fornecedorRepository.save(fornecedor);
            produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Fornecedor ou produto não encontrado.");
        }
    }

    @Override
    @Transactional
    public void removerProduto(UUID fornecedorId, UUID produtoId) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(fornecedorId);
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);

        if (fornecedorOptional.isPresent() && produtoOptional.isPresent()) {
            Fornecedor fornecedor = fornecedorOptional.get();
            Produto produto = produtoOptional.get();

            fornecedor.getProdutos().remove(produto);
            produto.getFornecedores().remove(fornecedor);

            fornecedorRepository.save(fornecedor);
            produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Fornecedor ou produto não encontrado.");
        }
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        fornecedorRepository.deleteById(id);
    }
}