package br.com.appfastfood.interfaces.usecases;

import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.entities.StatusDoPedido;

import java.util.UUID;

public interface AtualizarStatusPedidoUseCasePort {

    Pedido execute(UUID pedidoId, StatusDoPedido statusDoPedido);
}
