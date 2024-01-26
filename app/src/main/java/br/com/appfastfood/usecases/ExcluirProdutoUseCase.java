package br.com.appfastfood.usecases;

import br.com.appfastfood.interfaces.usecases.ExcluirProdutoUseCasePort;
import br.com.appfastfood.interfaces.gateways.ProdutoGatewayPort;

import java.util.UUID;

public class ExcluirProdutoUseCase implements ExcluirProdutoUseCasePort {

    private final ProdutoGatewayPort produtoGateway;

    public ExcluirProdutoUseCase(ProdutoGatewayPort produtoGateway) {

        this.produtoGateway = produtoGateway;
    }
    @Override
    public void execute(UUID id) {
        produtoGateway.excluir(id);
    }
}
