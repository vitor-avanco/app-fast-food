package br.com.appfastfood.usecases;
import br.com.appfastfood.entities.Produto;
import br.com.appfastfood.entities.TipoDeProduto;
import br.com.appfastfood.interfaces.gateways.ProdutoGatewayPort;
import br.com.appfastfood.interfaces.usecases.ObterProdutosPorCategoriaUseCasePort;

import java.util.List;

public class ObterProdutosPorCategoriaUseCase implements ObterProdutosPorCategoriaUseCasePort {
    private final ProdutoGatewayPort produtoGateway;

    public ObterProdutosPorCategoriaUseCase(ProdutoGatewayPort produtoGateway) {

        this.produtoGateway = produtoGateway;
    }

    @Override
    public List<Produto> execute(TipoDeProduto categoria) {
        return produtoGateway.obterProdutosPor(categoria);
    }
}
