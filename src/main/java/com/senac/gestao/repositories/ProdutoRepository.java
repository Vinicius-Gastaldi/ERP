package com.senac.gestao.repositories;

import com.senac.gestao.models.Produto;
import com.senac.gestao.models.enums.CategoriaProdutoEnum;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends BaseRepository<Produto, UUID> {
    Page<Produto> findByCategoria(CategoriaProdutoEnum categoria, Pageable pageable);
}
