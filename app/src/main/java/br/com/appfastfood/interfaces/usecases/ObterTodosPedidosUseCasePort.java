package br.com.appfastfood.interfaces.usecases;

import br.com.appfastfood.entities.Pedido;

import java.util.List;

public interface ObterTodosPedidosUseCasePort {
    List<Pedido> execute();
}
