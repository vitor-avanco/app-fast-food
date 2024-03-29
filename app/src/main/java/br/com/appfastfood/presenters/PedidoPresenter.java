package br.com.appfastfood.presenters;

import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.entities.StatusDoPagamento;
import br.com.appfastfood.entities.StatusDoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoPresenter {
    private UUID id;
    private BigDecimal preco;
    private StatusDoPedido statusDoPedido;
    private StatusDoPagamento statusDoPagamento;
    private String qrCode;
    private List<ItemDoPedidoPresenter> itens;
    private UUID pagamentoId;
    private LocalDateTime dataDeCriacao;

    public UUID getId() {
        return id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public StatusDoPedido getStatusDoPedido() {
        return statusDoPedido;
    }

    public StatusDoPagamento getStatusDoPagamento() {
        return statusDoPagamento;
    }

    public List<ItemDoPedidoPresenter> getItens() {
        return itens;
    }

    public static PedidoPresenter fromDomain(Pedido pedido) {
        PedidoPresenter pedidoPresenter = new PedidoPresenter();

        pedidoPresenter.id = pedido.getId();
        pedidoPresenter.preco = pedido.getPreco();
        pedidoPresenter.statusDoPedido = pedido.getStatusDoPedido();
        pedidoPresenter.statusDoPagamento = pedido.getStatusDoPagamento();
        pedidoPresenter.qrCode = pedido.getQrCode();
        pedidoPresenter.itens = pedido.getItens().stream().map(ItemDoPedidoPresenter::fromDomain).collect(Collectors.toList());
        pedidoPresenter.pagamentoId = pedido.getPagamentoId();
        pedidoPresenter.dataDeCriacao = pedido.getDataDeCriacao();

        return pedidoPresenter;
    }

    public UUID getPagamentoId() {
        return pagamentoId;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public String getQrCode() {
        return qrCode;
    }
}
