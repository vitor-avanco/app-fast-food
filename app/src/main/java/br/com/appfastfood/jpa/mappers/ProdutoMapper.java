package br.com.appfastfood.jpa.mappers;

import br.com.appfastfood.jpa.entities.ProdutoJpaEntity;
import br.com.appfastfood.entities.Produto;

public class ProdutoMapper {

    public static Produto toDomain(ProdutoJpaEntity entity) {
        return new Produto(entity.getId(), entity.getNome(), entity.getCategoria(), entity.getPreco(), entity.getDescricao(), entity.getImagem());
    }

    public static ProdutoJpaEntity toEntity(Produto produto) {
        ProdutoJpaEntity produtoJpaEntity = new ProdutoJpaEntity();

        produtoJpaEntity.setId(produto.getId());
        produtoJpaEntity.setNome(produto.getNome());
        produtoJpaEntity.setCategoria(produto.getCategoria());
        produtoJpaEntity.setPreco(produto.getPreco());
        produtoJpaEntity.setDescricao(produto.getDescricao());
        produtoJpaEntity.setImagem(produto.getImagem());

        return produtoJpaEntity;
    }
}
