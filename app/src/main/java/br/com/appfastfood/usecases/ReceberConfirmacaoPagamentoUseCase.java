package br.com.appfastfood.usecases;

import br.com.appfastfood.exceptions.ConfirmacaoDePagamentoInvalidaException;
import br.com.appfastfood.exceptions.PedidoNaoEncontradoException;
import br.com.appfastfood.usecases.model.ComandoDeConfirmacaoDePagamento;
import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.entities.StatusDoPagamento;
import br.com.appfastfood.entities.StatusDoPedido;
import br.com.appfastfood.interfaces.gateways.PedidoGatewayPort;
import br.com.appfastfood.interfaces.usecases.ReceberConfirmacaoPagamentoUseCasePort;

import java.util.Optional;
import java.util.UUID;

public class ReceberConfirmacaoPagamentoUseCase implements ReceberConfirmacaoPagamentoUseCasePort {
    private final PedidoGatewayPort pedidoGateway;

    public ReceberConfirmacaoPagamentoUseCase(PedidoGatewayPort pedidoGateway) {

        this.pedidoGateway = pedidoGateway;
    }

    public String execute(ComandoDeConfirmacaoDePagamento comandoDeConfirmacaoDePagamento) {
        String action = comandoDeConfirmacaoDePagamento.getAction();
        if (! action.equals("payment.created")) {
            throw ConfirmacaoDePagamentoInvalidaException.aPartirDaAction(action);
        }

        UUID pagamentoId = comandoDeConfirmacaoDePagamento.getPagamentoId();
        Optional<Pedido> pedidoO = pedidoGateway.obterPedidoComPagamentoId(pagamentoId);
        if (pedidoO.isEmpty()) {
            throw PedidoNaoEncontradoException.aPartirDoPagamentoId(pagamentoId);
        }
        
        Pedido pedido = pedidoO.get();
        pedido.setStatusDoPagamento(StatusDoPagamento.APROVADO);
        pedido.setStatusDoPedido(StatusDoPedido.EM_PREPARACAO);
        pedido.setQrCode("");
        pedidoGateway.atualizarPedido(pedido);
        return String.format("Pagamento %s confirmado", pagamentoId);
    }
}
