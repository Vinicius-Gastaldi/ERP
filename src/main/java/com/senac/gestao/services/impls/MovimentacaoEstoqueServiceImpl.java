package com.senac.gestao.services.impls;

import com.senac.gestao.dtos.MovimentacaoRequest;
import com.senac.gestao.dtos.MovimentacaoResponse;
import com.senac.gestao.models.MovimentacaoEstoque;
import com.senac.gestao.models.enums.TipoMovimentacaoEnum;
import com.senac.gestao.repositories.MovimentacaoEstoqueRepository;
import com.senac.gestao.services.MovimentacaoEstoqueService;
import org.hibernate.validator.constraints.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MovimentacaoEstoqueServiceImpl implements MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    private final ModelMapper modelMapper;

    public MovimentacaoEstoqueServiceImpl(MovimentacaoEstoqueRepository movimentacaoEstoqueRepository, ModelMapper modelMapper) {
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public MovimentacaoResponse criar(MovimentacaoRequest request) {
        MovimentacaoEstoque movimentacao = modelMapper.map(request, MovimentacaoEstoque.class);
        movimentacao.setDataCriacao(LocalDateTime.now()); // Define a data da movimentação
        movimentacao = movimentacaoEstoqueRepository.save(movimentacao);
        return modelMapper.map(movimentacao, MovimentacaoResponse.class);
    }

    @Override
    @Transactional
    public MovimentacaoResponse reverter(UUID movimentacaoId) {
        Optional<MovimentacaoEstoque> movimentacaoOptional = movimentacaoEstoqueRepository.findById(movimentacaoId);
        if (movimentacaoOptional.isPresent()) {
            MovimentacaoEstoque movimentacao = movimentacaoOptional.get();
            MovimentacaoEstoque reversao = MovimentacaoEstoque.builder()
                    .tipo(movimentacao.getTipo() == TipoMovimentacaoEnum.ENTRADA ? TipoMovimentacaoEnum.SAIDA : TipoMovimentacaoEnum.ENTRADA)
                    .quantidade(movimentacao.getQuantidade())
                    .produto(movimentacao.getProduto())
                    .estoque(movimentacao.getEstoque())
                    .responsavel(movimentacao.getResponsavel())
                    .descricao("Reversão da movimentação: " + movimentacao.getId())
                    .dataCriacao(LocalDateTime.now())
                    .build();
            reversao = movimentacaoEstoqueRepository.save(reversao);
            return modelMapper.map(reversao, MovimentacaoResponse.class);
        }
        throw new RuntimeException("Movimentação não encontrada com o ID: " + movimentacaoId);
    }

    @Override
    public MovimentacaoResponse buscarPorId(UUID id) {
        Optional<MovimentacaoEstoque> movimentacaoOptional = movimentacaoEstoqueRepository.findById(id);
        if (movimentacaoOptional.isPresent()) {
            return modelMapper.map(movimentacaoOptional.get(), MovimentacaoResponse.class);
        }
        throw new RuntimeException("Movimentação não encontrada com o ID: " + id);
    }

    @Override
    public Page<MovimentacaoResponse> listarPorEstoque(UUID estoqueId, Pageable pageable) {
        Page<MovimentacaoEstoque> movimentacoes = movimentacaoEstoqueRepository.findPorEstoqueEPeriodo(
                estoqueId, LocalDateTime.MIN, LocalDateTime.MAX, pageable); // Busca todas as movimentações do estoque
        return movimentacoes.map(movimentacao -> modelMapper.map(movimentacao, MovimentacaoResponse.class));
    }

    @Override
    public Page<MovimentacaoResponse> listarPorProduto(UUID produtoId, Pageable pageable) {
        Page<MovimentacaoEstoque> movimentacoes = movimentacaoEstoqueRepository.findEntradasPorProduto(produtoId, pageable);
        return movimentacoes.map(movimentacao -> modelMapper.map(movimentacao, MovimentacaoResponse.class));
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        movimentacaoEstoqueRepository.deleteById(id);
    }
}