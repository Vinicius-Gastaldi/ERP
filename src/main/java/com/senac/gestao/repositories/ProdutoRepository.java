package com.senac.gestao.repositories;

import com.senac.gestao.models.Produto;
import com.senac.gestao.models.enums.CategoriaProdutoEnum;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends BaseRepository<Produto, UUID> {

    Optional<Produto> findBySku(String sku);

    @Query("SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Produto> searchByNome(@Param("termo") String termo, Pageable pageable);

    Page<Produto> findByCategoria(CategoriaProdutoEnum categoria, Pageable pageable);

    @Query("SELECT p FROM Produto p JOIN p.fornecedores f WHERE f.id = :fornecedorId")
    Page<Produto> findByFornecedorId(@Param("fornecedorId") UUID fornecedorId, Pageable pageable);

    boolean existsBySku(String sku);

    @Query("SELECT p FROM Produto p JOIN p.estoques e WHERE e.id = :estoqueId")
    Page<Produto> findByEstoqueId(@Param("estoqueId") UUID estoqueId, Pageable pageable);
}
