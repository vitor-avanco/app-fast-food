package br.com.appfastfood.usecases;

import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.entities.StatusDoPedido;

import br.com.appfastfood.interfaces.gateways.PedidoGatewayPort;
import br.com.appfastfood.interfaces.usecases.ObterPedidosPorStatusUseCasePort;

import java.util.List;

public class ObterPedidosPorStatusUseCase implements ObterPedidosPorStatusUseCasePort {

    private final PedidoGatewayPort pedidoGateway;

    public ObterPedidosPorStatusUseCase(PedidoGatewayPort pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public List<Pedido> execute(StatusDoPedido... statuses) {
        return pedidoGateway.obterPedidosPorStatus(statuses);
    }
}

