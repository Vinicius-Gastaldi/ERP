package com.senac.gestao.repositories;

import com.senac.gestao.models.Fornecedor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends BaseRepository<Fornecedor, UUID> {
}
