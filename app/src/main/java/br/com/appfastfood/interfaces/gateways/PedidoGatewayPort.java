package br.com.appfastfood.interfaces.gateways;

import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.entities.StatusDoPedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoGatewayPort {
    Pedido inserirPedido(Pedido pedido);

    Pedido obterPedido(UUID pedidoId);

    List<Pedido> obterTodosPedidos();

    Boolean consultarStatusPagamento(UUID pedidoId);

    void atualizarPedido(Pedido pedido);

    Optional<Pedido> obterPedidoComPagamentoId(UUID pagamentoId);

    List<Pedido> obterPedidosPorStatus(StatusDoPedido... statuses);
}
