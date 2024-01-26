package br.com.appfastfood.config;

import br.com.appfastfood.controllers.ClienteController;
import br.com.appfastfood.controllers.PagamentoController;
import br.com.appfastfood.controllers.PedidoController;
import br.com.appfastfood.controllers.ProdutoController;
import br.com.appfastfood.interfaces.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerBeanConfig {

    @Bean
    public ClienteController clienteController(CadastrarClienteUseCasePort cadastrarClienteUseCase,
                                               IdentificarClienteUseCasePort identificarClienteUseCase) {
        return new ClienteController(cadastrarClienteUseCase, identificarClienteUseCase);
    }

    @Bean
    public PedidoController pedidoController(FazerCheckoutPedidoUseCasePort fazerCheckoutPedidoUseCase,
                                             ObterTodosPedidosUseCasePort obterTodosPedidosUseCase,
                                             ObterPedidosPorStatusUseCasePort obterPedidosPorStatusUseCase,
                                             AtualizarStatusPedidoUseCasePort atualizarStatusPedidoUseCase) {
        return new PedidoController(fazerCheckoutPedidoUseCase, obterTodosPedidosUseCase, obterPedidosPorStatusUseCase, atualizarStatusPedidoUseCase);
    }

    @Bean
    public ProdutoController produtoController(CadastrarProdutoUseCasePort cadastrarProdutoUseCase,
                                               EditarProdutoUseCasePort editarProdutoUseCase,
                                               ExcluirProdutoUseCasePort excluirProdutoUseCase,
                                               ObterProdutosPorCategoriaUseCasePort obterProdutosPorCategoriaUseCase) {
        return new ProdutoController(cadastrarProdutoUseCase, editarProdutoUseCase, excluirProdutoUseCase, obterProdutosPorCategoriaUseCase);
    }

    @Bean
    public PagamentoController pagamentoController(ConsultarStatusPagamentoUseCasePort consultarStatusPagamentoUseCase,
                                                   ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase) {
        return new PagamentoController(consultarStatusPagamentoUseCase, receberConfirmacaoPagamentoUseCase);
    }
}
