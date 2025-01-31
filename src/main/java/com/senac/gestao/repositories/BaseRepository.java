package com.senac.gestao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    // Método genérico para busca paginada
    Page<T> findAll(Pageable pageable);

    // Método para arquivamento lógico (se houver campo ativo)
    @Modifying
    @Query("UPDATE #{#entityName} e SET e.ativo = false WHERE e.id = :id")
    void archive(@Param("id") ID id);
}
