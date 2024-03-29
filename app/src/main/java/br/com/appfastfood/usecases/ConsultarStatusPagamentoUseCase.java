package br.com.appfastfood.usecases;

import br.com.appfastfood.interfaces.gateways.PedidoGatewayPort;
import br.com.appfastfood.interfaces.usecases.ConsultarStatusPagamentoUseCasePort;

import java.util.UUID;

public class ConsultarStatusPagamentoUseCase implements ConsultarStatusPagamentoUseCasePort {
    private final PedidoGatewayPort pedidoGateway;

    public ConsultarStatusPagamentoUseCase(PedidoGatewayPort pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Boolean execute(UUID pedidoId) {
        return pedidoGateway.consultarStatusPagamento(pedidoId);
    }
}
