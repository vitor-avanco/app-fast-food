package br.com.appfastfood.usecases;

import br.com.appfastfood.entities.Pedido;
import br.com.appfastfood.entities.StatusDoPagamento;
import br.com.appfastfood.interfaces.gateways.PedidoGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.appfastfood.exceptions.StatusPedidoNaoAtualizadoException;
import br.com.appfastfood.entities.StatusDoPedido;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class AtualizarStatusPedidoUseCaseTest {

    @Mock
    private PedidoGatewayPort pedidoGateway;

    @InjectMocks
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecutePedidoAprovadoStatusValido() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        StatusDoPedido statusDoPedido = StatusDoPedido.RECEBIDO;
        Pedido pedido = new Pedido();
        pedido.setStatusDoPagamento(StatusDoPagamento.APROVADO);
        pedido.setStatusDoPedido(statusDoPedido);

        when(pedidoGateway.obterPedido(pedidoId)).thenReturn(pedido);

        // Act
        Pedido resultado = atualizarStatusPedidoUseCase.execute(pedidoId, StatusDoPedido.EM_PREPARACAO);

        // Assert
        assertEquals(StatusDoPedido.EM_PREPARACAO, resultado.getStatusDoPedido());
        verify(pedidoGateway, times(1)).atualizarPedido(pedido);
    }

    @Test
    public void testExecutePedidoNaoAprovado() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        StatusDoPedido statusDoPedido = StatusDoPedido.FINALIZADO;
        Pedido pedido = new Pedido();
        pedido.setStatusDoPagamento(StatusDoPagamento.RECUSADO);

        when(pedidoGateway.obterPedido(pedidoId)).thenReturn(pedido);

        // Act and Assert
        assertThrows(StatusPedidoNaoAtualizadoException.class, () ->
                atualizarStatusPedidoUseCase.execute(pedidoId, statusDoPedido));

        verify(pedidoGateway, never()).atualizarPedido(any());
    }
}