package br.com.appfastfood.usecases;

import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.interfaces.usecases.ObterTodosPedidosUseCasePort;
import br.com.appfastfood.interfaces.gateways.PedidoGatewayPort;

import java.util.List;

public class ObterTodosPedidosUseCase implements ObterTodosPedidosUseCasePort {
    private final PedidoGatewayPort pedidoGateway;

    public ObterTodosPedidosUseCase(PedidoGatewayPort pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public List<Pedido> execute() {
        return pedidoGateway.obterTodosPedidos();
    }
}
