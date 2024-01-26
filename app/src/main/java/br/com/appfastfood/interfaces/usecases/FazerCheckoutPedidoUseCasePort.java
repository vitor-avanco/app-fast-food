package br.com.appfastfood.interfaces.usecases;

import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.usecases.model.ComandoDeNovoPedido;

public interface FazerCheckoutPedidoUseCasePort {
    Pedido execute(ComandoDeNovoPedido comandoDeNovoPedido);
}
