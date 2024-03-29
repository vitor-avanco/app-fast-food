package br.com.appfastfood.controllers;

import br.com.appfastfood.usecases.model.ComandoDeNovoPedido;
import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.entities.StatusDoPedido;
import br.com.appfastfood.interfaces.usecases.AtualizarStatusPedidoUseCasePort;
import br.com.appfastfood.interfaces.usecases.FazerCheckoutPedidoUseCasePort;
import br.com.appfastfood.interfaces.usecases.ObterPedidosPorStatusUseCasePort;
import br.com.appfastfood.interfaces.usecases.ObterTodosPedidosUseCasePort;
import br.com.appfastfood.presenters.PedidoPresenter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoController {

    private final FazerCheckoutPedidoUseCasePort fazerCheckoutPedidoUseCase;
    private final ObterTodosPedidosUseCasePort obterTodosPedidosUseCase;
    private final ObterPedidosPorStatusUseCasePort obterPedidosPorStatusUseCase;
    private final AtualizarStatusPedidoUseCasePort atualizarStatusPedidoUseCase;

    public PedidoController(FazerCheckoutPedidoUseCasePort fazerCheckoutPedidoUseCase,
                            ObterTodosPedidosUseCasePort obterTodosPedidosUseCase,
                            ObterPedidosPorStatusUseCasePort obterPedidosPorStatusUseCase,
                            AtualizarStatusPedidoUseCasePort atualizarStatusPedidoUseCase) {
        this.fazerCheckoutPedidoUseCase = fazerCheckoutPedidoUseCase;
        this.obterTodosPedidosUseCase = obterTodosPedidosUseCase;
        this.obterPedidosPorStatusUseCase = obterPedidosPorStatusUseCase;
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
    }

    public PedidoPresenter fazerCheckoutPedido(ComandoDeNovoPedido comandoDeNovoPedido) {
        Pedido pedido = fazerCheckoutPedidoUseCase.execute(comandoDeNovoPedido);
        return PedidoPresenter.fromDomain(pedido);
    }

    public List<PedidoPresenter> obterTodosPedidos() {
        List<Pedido> pedidos = obterTodosPedidosUseCase.execute();
        return pedidos.stream().map(PedidoPresenter::fromDomain).collect(Collectors.toList());
    }

    public List<PedidoPresenter> obterPedidosPorStatus(StatusDoPedido... statuses) {
        List<Pedido> pedidos = obterPedidosPorStatusUseCase.execute(statuses);
        return pedidos.stream().map(PedidoPresenter::fromDomain).collect(Collectors.toList());
    }

    public PedidoPresenter atualizarStatusPedido(UUID pedidoId, StatusDoPedido statusDoPedido) {
        Pedido pedido = atualizarStatusPedidoUseCase.execute(pedidoId, statusDoPedido);
        return PedidoPresenter.fromDomain(pedido);
    }
}
