package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.EstoqueRequest;
import com.senac.gestao.dtos.EstoqueResponse;
import com.senac.gestao.models.Estoque;
import com.senac.gestao.models.Produto;
import com.senac.gestao.repositories.EstoqueRepository;
import com.senac.gestao.repositories.ProdutoRepository;
import com.senac.gestao.services.EstoqueService;
import org.hibernate.validator.constraints.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    private final EstoqueRepository estoqueRepository;

    private final ProdutoRepository produtoRepository;

    private final ModelMapper modelMapper;

    public EstoqueServiceImpl(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository, ModelMapper modelMapper) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public EstoqueResponse criar(EstoqueRequest request) {
        Estoque estoque = modelMapper.map(request, Estoque.class);
        estoque.setCapacidadeUsada(BigDecimal.ZERO); // Define a capacidade usada inicial como zero
        estoque = estoqueRepository.save(estoque);
        return modelMapper.map(estoque, EstoqueResponse.class);
    }

    @Override
    @Transactional
    public EstoqueResponse atualizar(UUID id, EstoqueRequest request) {
        Optional<Estoque> estoqueOptional = estoqueRepository.findById(id);
        if (estoqueOptional.isPresent()) {
            Estoque estoque = estoqueOptional.get();
            modelMapper.map(request, estoque);
            estoque = estoqueRepository.save(estoque);
            return modelMapper.map(estoque, EstoqueResponse.class);
        }
        throw new RuntimeException("Estoque não encontrado com o ID: " + id);
    }

    @Override
    public EstoqueResponse buscarPorId(UUID id) {
        Optional<Estoque> estoqueOptional = estoqueRepository.findById(id);
        if (estoqueOptional.isPresent()) {
            return modelMapper.map(estoqueOptional.get(), EstoqueResponse.class);
        }
        throw new RuntimeException("Estoque não encontrado com o ID: " + id);
    }

    @Override
    public Page<EstoqueResponse> listarTodos(Pageable pageable) {
        Page<Estoque> estoques = estoqueRepository.findAll(pageable);
        return estoques.map(estoque -> modelMapper.map(estoque, EstoqueResponse.class));
    }

    @Override
    @Transactional
    public void adicionarProduto(UUID estoqueId, UUID produtoId) {
        Optional<Estoque> estoqueOptional = estoqueRepository.findById(estoqueId);
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);

        if (estoqueOptional.isPresent() && produtoOptional.isPresent()) {
            Estoque estoque = estoqueOptional.get();
            Produto produto = produtoOptional.get();

            // Verifica se há capacidade disponível no estoque
            BigDecimal capacidadeDisponivel = estoque.getCapacidadeTotal().subtract(estoque.getCapacidadeUsada());
            if (capacidadeDisponivel.compareTo(BigDecimal.ONE) >= 0) { // Ajuste conforme a lógica de capacidade do produto
                estoque.getProdutos().add(produto);
                produto.getEstoques().add(estoque);

                // Atualiza a capacidade usada do estoque
                estoque.setCapacidadeUsada(estoque.getCapacidadeUsada().add(BigDecimal.ONE)); // Ajuste conforme a lógica de capacidade do produto

                estoqueRepository.save(estoque);
                produtoRepository.save(produto);
            } else {
                throw new RuntimeException("Capacidade insuficiente no estoque.");
            }
        } else {
            throw new RuntimeException("Estoque ou produto não encontrado.");
        }
    }

    @Override
    @Transactional
    public void removerProduto(UUID estoqueId, UUID produtoId) {
        Optional<Estoque> estoqueOptional = estoqueRepository.findById(estoqueId);
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);

        if (estoqueOptional.isPresent() && produtoOptional.isPresent()) {
            Estoque estoque = estoqueOptional.get();
            Produto produto = produtoOptional.get();

            estoque.getProdutos().remove(produto);
            produto.getEstoques().remove(estoque);

            // Atualiza a capacidade usada do estoque
            estoque.setCapacidadeUsada(estoque.getCapacidadeUsada().subtract(BigDecimal.ONE)); // Ajuste conforme a lógica de capacidade do produto

            estoqueRepository.save(estoque);
            produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Estoque ou produto não encontrado.");
        }
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        estoqueRepository.deleteById(id);
    }
}