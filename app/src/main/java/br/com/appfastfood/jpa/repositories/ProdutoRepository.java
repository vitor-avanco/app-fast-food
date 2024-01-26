package br.com.appfastfood.jpa.repositories;
import br.com.appfastfood.entities.TipoDeProduto;
import br.com.appfastfood.jpa.entities.ProdutoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<ProdutoJpaEntity, UUID> {
    List<ProdutoJpaEntity> findByCategoria(TipoDeProduto categoria);
}
