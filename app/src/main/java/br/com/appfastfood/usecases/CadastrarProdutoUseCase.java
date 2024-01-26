package br.com.appfastfood.usecases;
import br.com.appfastfood.entities.Produto;
import br.com.appfastfood.interfaces.usecases.CadastrarProdutoUseCasePort;
import br.com.appfastfood.interfaces.gateways.ProdutoGatewayPort;

public class CadastrarProdutoUseCase implements CadastrarProdutoUseCasePort {
    private final ProdutoGatewayPort produtoGateway;

    public CadastrarProdutoUseCase(ProdutoGatewayPort produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto execute(Produto produto) {
        return produtoGateway.cadastrar(produto);
    }
}
