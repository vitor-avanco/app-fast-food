package br.com.appfastfood.usecases;

import br.com.appfastfood.entities.Produto;
import br.com.appfastfood.interfaces.usecases.EditarProdutoUseCasePort;
import br.com.appfastfood.interfaces.gateways.ProdutoGatewayPort;

public class EditarProdutoUseCase implements EditarProdutoUseCasePort {

    private final ProdutoGatewayPort produtoGateway;

    public EditarProdutoUseCase(ProdutoGatewayPort produtoGateway) {
        this.produtoGateway = produtoGateway;
    }
    @Override
    public Produto execute(Produto produto) {
        return produtoGateway.editar(produto);
    }
}
