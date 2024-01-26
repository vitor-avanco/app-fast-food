package br.com.appfastfood.interfaces.gateways;
import br.com.appfastfood.entities.Produto;
import br.com.appfastfood.entities.TipoDeProduto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoGatewayPort {
    Produto cadastrar(Produto produto);
    List<Produto> obterProdutosPor(TipoDeProduto categoria);
    Optional<Produto> identificarPorId(UUID id);

    Produto editar(Produto produto);

    void excluir(UUID id);
}
