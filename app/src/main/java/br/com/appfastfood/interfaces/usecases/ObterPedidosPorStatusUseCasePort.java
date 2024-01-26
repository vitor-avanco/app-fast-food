package br.com.appfastfood.interfaces.usecases;

import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.entities.StatusDoPedido;

import java.util.List;

public interface ObterPedidosPorStatusUseCasePort {
     List<Pedido> execute(StatusDoPedido... statuses);
}
