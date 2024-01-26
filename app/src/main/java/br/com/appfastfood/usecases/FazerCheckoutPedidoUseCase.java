package br.com.appfastfood.usecases;

import br.com.appfastfood.entities.*;
import br.com.appfastfood.exceptions.ClienteNaoEncontradoException;
import br.com.appfastfood.exceptions.ProdutoNaoEncontradoException;
import br.com.appfastfood.usecases.model.ComandoDeNovoPedido;
import br.com.appfastfood.usecases.model.ItemDoComandoDeNovoPedido;
import br.com.appfastfood.interfaces.gateways.ClienteGatewayPort;
import br.com.appfastfood.interfaces.gateways.ProdutoGatewayPort;
import br.com.appfastfood.interfaces.gateways.PedidoGatewayPort;
import br.com.appfastfood.interfaces.usecases.FazerCheckoutPedidoUseCasePort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FazerCheckoutPedidoUseCase implements FazerCheckoutPedidoUseCasePort {
    public static final String QR_CODE_MOCK = "00020101021243650016COM.MERCADOLIBRE02013063638f1192a-5fd1-4180-a180-8bcae3556bc35204000053039865802BR5925IZABEL AAAA DE MELO6007BARUERI62070503***63040B6D";
    private final PedidoGatewayPort pedidoGateway;
    private final ProdutoGatewayPort produtoGateway;
    private final ClienteGatewayPort clienteGateway;

    public FazerCheckoutPedidoUseCase(
            PedidoGatewayPort pedidoGateway,
            ProdutoGatewayPort produtoGateway,
            ClienteGatewayPort clienteGateway
    ) {
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = produtoGateway;
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Pedido execute(ComandoDeNovoPedido comandoDeNovoPedido) {
        Pedido pedido = new Pedido();

        pedido.setStatusDoPedido(StatusDoPedido.RECEBIDO);
        pedido.setStatusDoPagamento(StatusDoPagamento.PENDENTE);
        pedido.setDataDeCriacao(LocalDateTime.now());

        pedido.setPagamentoId(UUID.randomUUID());
        pedido.setQrCode(QR_CODE_MOCK);

        if (comandoDeNovoPedido.getClienteId() != null) {
            Cliente cliente = clienteGateway
                    .identificaPorId(comandoDeNovoPedido.getClienteId())
                    .orElseThrow(() -> ClienteNaoEncontradoException.aPartirDoId(comandoDeNovoPedido.getClienteId()));

            pedido.setClienteId(cliente.getId());
        }


        for (ItemDoComandoDeNovoPedido itemSolicitado : comandoDeNovoPedido.getItens()) {
            Produto produto = produtoGateway
                    .identificarPorId(itemSolicitado.getProdutoId())
                    .orElseThrow(() -> ProdutoNaoEncontradoException.aPartirDeProdutoId(itemSolicitado.getProdutoId()));

            ItemDoPedido item = new ItemDoPedido(
                    null,
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getCategoria().toString(),
                    produto.getImagem(),
                    itemSolicitado.getQuantidade(),
                    BigDecimal.valueOf(produto.getPreco())
            );

            pedido.adicionarItem(item);
        }

        return pedidoGateway.inserirPedido(pedido);
    }
}
