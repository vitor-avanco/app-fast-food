package br.com.appfastfood.usecases;

import br.com.appfastfood.exceptions.StatusPedidoNaoAtualizadoException;
import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.entities.StatusDoPedido;
import br.com.appfastfood.interfaces.gateways.PedidoGatewayPort;
import br.com.appfastfood.interfaces.usecases.AtualizarStatusPedidoUseCasePort;

import java.util.UUID;

public class AtualizarStatusPedidoUseCase implements AtualizarStatusPedidoUseCasePort {

    private final PedidoGatewayPort pedidoGateway;

    public AtualizarStatusPedidoUseCase(PedidoGatewayPort pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }
    @Override
    public Pedido execute(UUID pedidoId, StatusDoPedido statusDoPedido) {
        var pedido = pedidoGateway.obterPedido(pedidoId);

        if (! pedido.isPagamentoAprovado()) {
            throw StatusPedidoNaoAtualizadoException.porPagamentoNaoAprovado();
        }
        if (! pedido.getStatusDoPedido().podeAtualizarPara(statusDoPedido)) {
            throw StatusPedidoNaoAtualizadoException.porProximoStatusInvalido(pedido.getStatusDoPedido(), statusDoPedido);
        }

        pedido.setStatusDoPedido(statusDoPedido);
        pedidoGateway.atualizarPedido(pedido);
        return pedido;
    }
}
