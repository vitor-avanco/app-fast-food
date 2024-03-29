package br.com.appfastfood.config;

import br.com.appfastfood.interfaces.usecases.*;
import br.com.appfastfood.usecases.*;
import br.com.appfastfood.interfaces.gateways.PedidoGatewayPort;
import br.com.appfastfood.interfaces.gateways.ClienteGatewayPort;
import br.com.appfastfood.interfaces.gateways.ProdutoGatewayPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {

    @Bean
    public CadastrarClienteUseCasePort cadastrarClienteUseCasePort(ClienteGatewayPort clienteGatewayPort) {
        return new CadastrarClienteUseCase(clienteGatewayPort);
    }

    @Bean
    public IdentificarClienteUseCasePort identificarClienteUseCasePort(ClienteGatewayPort clienteGatewayPort) {
        return new IdentificarClienteUseCase(clienteGatewayPort);
    }

    @Bean
    public CadastrarProdutoUseCase cadastrarProdutoUseCasePort(ProdutoGatewayPort produtoGatewayPort) {
        return new CadastrarProdutoUseCase(produtoGatewayPort);
    }

    @Bean
    public EditarProdutoUseCase editarProdutoUseCasePort(ProdutoGatewayPort produtoGatewayPort) {
        return new EditarProdutoUseCase(produtoGatewayPort);
    }

    @Bean
    public ExcluirProdutoUseCase excluirProdutoUseCasePort(ProdutoGatewayPort produtoGatewayPort) {
        return new ExcluirProdutoUseCase(produtoGatewayPort);
    }

    @Bean
    public ObterProdutosPorCategoriaUseCasePort identificarProdutoUseCasePort(ProdutoGatewayPort produtoGatewayPort) {
        return new ObterProdutosPorCategoriaUseCase(produtoGatewayPort);
    }

    @Bean
    public FazerCheckoutPedidoUseCasePort fazerCheckoutPedidoUseCasePort(PedidoGatewayPort pedidoGatewayPort, ProdutoGatewayPort produtoGatewayPort, ClienteGatewayPort clienteGatewayPort) {
        return new FazerCheckoutPedidoUseCase(pedidoGatewayPort, produtoGatewayPort, clienteGatewayPort);
    }

    @Bean
    public ObterTodosPedidosUseCasePort obterTodosPedidosUseCasePort(PedidoGatewayPort pedidoGatewayPort) {
        return new ObterTodosPedidosUseCase(pedidoGatewayPort);
    }

    @Bean
    public ObterPedidosPorStatusUseCasePort obterPedidosPorStatusUseCase(PedidoGatewayPort pedidoGatewayPort) {
        return new ObterPedidosPorStatusUseCase(pedidoGatewayPort);
    }

    @Bean
    public ConsultarStatusPagamentoUseCasePort consultarStatusPagamentoUseCasePort(PedidoGatewayPort pedidoGatewayPort) {
        return new ConsultarStatusPagamentoUseCase(pedidoGatewayPort);
    }

    @Bean
    public ReceberConfirmacaoPagamentoUseCasePort ReceberConfirmacaoPagamentoUseCasePort(PedidoGatewayPort pedidoGatewayPort) {
        return new ReceberConfirmacaoPagamentoUseCase(pedidoGatewayPort);
    }

    @Bean
    public AtualizarStatusPedidoUseCasePort atualizarStatusPedidoUseCasePort(PedidoGatewayPort pedidoGatewayPort) {
        return new AtualizarStatusPedidoUseCase(pedidoGatewayPort);
    }
}
