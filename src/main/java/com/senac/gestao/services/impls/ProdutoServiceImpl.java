package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.ProdutoRequest;
import com.senac.gestao.dtos.ProdutoResponse;
import com.senac.gestao.models.Produto;
import com.senac.gestao.models.enums.CategoriaProdutoEnum;
import com.senac.gestao.repositories.ProdutoRepository;
import com.senac.gestao.services.ProdutoService;
import org.hibernate.validator.constraints.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ModelMapper modelMapper;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ModelMapper modelMapper) {
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ProdutoResponse criar(ProdutoRequest request) {
        Produto produto = modelMapper.map(request, Produto.class);
        produto = produtoRepository.save(produto);
        return modelMapper.map(produto, ProdutoResponse.class);
    }

    @Override
    @Transactional
    public ProdutoResponse atualizar(UUID id, ProdutoRequest request) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            modelMapper.map(request, produto);
            produto = produtoRepository.save(produto);
            return modelMapper.map(produto, ProdutoResponse.class);
        }
        throw new RuntimeException("Produto não encontrado com o ID: " + id);
    }

    @Override
    public ProdutoResponse buscarPorId(UUID id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            return modelMapper.map(produtoOptional.get(), ProdutoResponse.class);
        }
        throw new RuntimeException("Produto não encontrado com o ID: " + id);
    }

    @Override
    public Page<ProdutoResponse> listarTodos(Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        return produtos.map(produto -> modelMapper.map(produto, ProdutoResponse.class));
    }

    @Override
    public Page<ProdutoResponse> listarPorCategoria(CategoriaProdutoEnum categoria, Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findByCategoria(categoria, pageable);
        return produtos.map(produto -> modelMapper.map(produto, ProdutoResponse.class));
    }

    @Override
    public List<ProdutoResponse> listarParaReposicao() {
        // Lógica para listar produtos que precisam de reposição
        List<Produto> produtos = produtoRepository.findAll(); // Exemplo básico, ajustar conforme necessário
        return produtos.stream()
                .filter(produto -> produto.getEstoques().isEmpty()) // Exemplo de filtro
                .map(produto -> modelMapper.map(produto, ProdutoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutoResponse> listarExcedentes() {
        // Lógica para listar produtos excedentes
        List<Produto> produtos = produtoRepository.findAll(); // Exemplo básico, ajustar conforme necessário
        return produtos.stream()
                .filter(produto -> produto.getEstoques().size() > 10) // Exemplo de filtro
                .map(produto -> modelMapper.map(produto, ProdutoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        produtoRepository.deleteById(id);
    }
}