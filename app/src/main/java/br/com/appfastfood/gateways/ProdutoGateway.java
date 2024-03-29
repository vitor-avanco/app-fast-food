package br.com.appfastfood.gateways;
import br.com.appfastfood.exceptions.ProdutoNaoEncontradoException;
import br.com.appfastfood.jpa.mappers.ProdutoMapper;
import br.com.appfastfood.entities.TipoDeProduto;
import br.com.appfastfood.jpa.entities.ProdutoJpaEntity;
import br.com.appfastfood.jpa.repositories.ProdutoRepository;
import br.com.appfastfood.entities.Produto;
import br.com.appfastfood.interfaces.gateways.ProdutoGatewayPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProdutoGateway implements ProdutoGatewayPort {
    private final ProdutoRepository produtoRepository;

    public ProdutoGateway(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Transactional
    public Produto cadastrar(Produto produto) {
        ProdutoJpaEntity produtoJpaEntity = ProdutoMapper.toEntity(produto);

        produtoRepository.save(produtoJpaEntity);

        produto.setId(produtoJpaEntity.getId());

        return produto;
    }

    @Override
    public List<Produto> obterProdutosPor(TipoDeProduto categoria) {
        var produtos = produtoRepository.findByCategoria(categoria);
        if (produtos.isEmpty()) {
            return Collections.emptyList();
        }

        return produtos.stream()
                .map(ProdutoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> identificarPorId(UUID id) {
        return produtoRepository.findById(id).map(ProdutoMapper::toDomain);
    }

    @Override
    public Produto editar(Produto produto) {
        var produtoO = produtoRepository.findById(produto.getId());
        if (produtoO.isEmpty()) {
            throw ProdutoNaoEncontradoException.aPartirDeProdutoId(produto.getId());
        }
        ProdutoJpaEntity produtoJpaEntity = ProdutoMapper.toEntity(produto);
        produtoRepository.save(produtoJpaEntity);
        return produto;
    }

    @Override
    public void excluir(UUID id) {
        var produtoO = produtoRepository.findById(id);
        if (produtoO.isEmpty()) {
            throw ProdutoNaoEncontradoException.aPartirDeProdutoId(id);
        }
        produtoRepository.deleteById(id);
    }
}
