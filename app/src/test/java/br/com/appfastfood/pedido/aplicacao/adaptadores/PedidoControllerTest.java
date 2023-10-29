package br.com.appfastfood.pedido.aplicacao.adaptadores;

import br.com.appfastfood.cliente.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.VO.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.servicos.portas.PedidoServico;
import br.com.appfastfood.pedido.exceptions.IDPedidoNaoEncontradoException;
import br.com.appfastfood.pedido.exceptions.PagamentoNaoRealizado;
import br.com.appfastfood.pedido.exceptions.PedidoJaFinalizadoException;
import br.com.appfastfood.produto.exceptions.CategoriaNaoEncontradaException;
import br.com.appfastfood.produto.exceptions.IDNaoEncontradoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

 public class PedidoControllerTest {
  @Mock
  private PedidoServico pedidoServico;

  @InjectMocks
  private PedidoController pedidoController;

  @BeforeEach
  void setUp() {
   MockitoAnnotations.openMocks(this);
  }

  @Test
  void criar_DeveRetornarPedidoCriado() throws IDNaoEncontradoException, PagamentoNaoRealizado {
   // Dados de entrada
   PedidoRequisicao pedidoRequisicao = PedidoRequisicao.builder()
           .produtos(Arrays.asList(ProdutosReq.builder()
                   .idProduto("1")
                   .quantidadeProduto("2")
                   .build()))
           .idCliente("123")
           .valorTotal(10.0)
           .status("RECEBIDO")
           .tempoEspera("1:00")
           .idPedido(null)
           .build();

   // Mock do serviço
   when(pedidoServico.criar(eq(pedidoRequisicao), eq("RECEBIDO"), eq("1:00")))
           .thenReturn("123456789");

   // Execução do método
   ResponseEntity<?> responseEntity = pedidoController.criar(pedidoRequisicao);

   // Verificações
   assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
   PedidoRequisicao responseBody = (PedidoRequisicao) responseEntity.getBody();
   assertEquals("123456789", responseBody.getIdPedido());
  }

  @Test
  void criar_DeveRetornarBadRequestQuandoIDNaoEncontradoException() throws IDNaoEncontradoException, PagamentoNaoRealizado {
   // Dados de entrada
   PedidoRequisicao pedidoRequisicao = PedidoRequisicao.builder()
           .produtos(Arrays.asList(ProdutosReq.builder()
                   .idProduto("1")
                   .quantidadeProduto("2")
                   .build()))
           .idCliente("123")
           .valorTotal(10.0)
           .status("RECEBIDO")
           .tempoEspera("1:00")
           .idPedido(null)
           .build();

   // Mock do serviço lançando exceção
   when(pedidoServico.criar(eq(pedidoRequisicao), eq("RECEBIDO"), eq("1:00")))
           .thenThrow(new IDNaoEncontradoException());

   // Execução do método
   ResponseEntity<?> responseEntity = pedidoController.criar(pedidoRequisicao);

   // Verificações
   assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
   RequisicaoExcecao responseBody = (RequisicaoExcecao) responseEntity.getBody();

  }

  @Test
  void criar_DeveRetornarBadRequestQuandoPagamentoNaoRealizado() throws IDNaoEncontradoException, PagamentoNaoRealizado {
   // Dados de entrada
   PedidoRequisicao pedidoRequisicao = PedidoRequisicao.builder()
           .produtos(Arrays.asList(ProdutosReq.builder()
                   .idProduto("1")
                   .quantidadeProduto("2")
                   .build()))
           .idCliente("123")
           .valorTotal(10.0)
           .status("RECEBIDO")
           .tempoEspera("1:00")
           .idPedido(null)
           .build();

   // Mock do serviço lançando exceção
   when(pedidoServico.criar(eq(pedidoRequisicao), eq("RECEBIDO"), eq("1:00")))
           .thenThrow(new PagamentoNaoRealizado());

   // Execução do método
   ResponseEntity<?> responseEntity = pedidoController.criar(pedidoRequisicao);

   // Verificações
   assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
   RequisicaoExcecao responseBody = (RequisicaoExcecao) responseEntity.getBody();

  }



  @Test
  void atualizarStatus_DeveRetornarBadRequestQuandoIDPedidoNaoEncontradoException() throws IDPedidoNaoEncontradoException, PedidoJaFinalizadoException {
   // Dados de entrada
   Long idPedido = 123L;

   // Mock do serviço lançando exceção
   when(pedidoServico.atualizar(eq(idPedido))).thenThrow(new IDPedidoNaoEncontradoException());

   // Execução do método
   ResponseEntity<?> responseEntity = pedidoController.atualizarStatus(idPedido);

   // Verificações
   assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
   RequisicaoExcecao responseBody = (RequisicaoExcecao) responseEntity.getBody();

  }

  @Test
  void atualizarStatus_DeveRetornarBadRequestQuandoPedidoJaFinalizadoException() throws IDPedidoNaoEncontradoException, PedidoJaFinalizadoException {
   // Dados de entrada
   Long idPedido = 123L;

   // Mock do serviço lançando exceção
   when(pedidoServico.atualizar(eq(idPedido))).thenThrow(new PedidoJaFinalizadoException());

   // Execução do método
   ResponseEntity<?> responseEntity = pedidoController.atualizarStatus(idPedido);

   // Verificações
   assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
   RequisicaoExcecao responseBody = (RequisicaoExcecao) responseEntity.getBody();

  }



  @Test
  void buscarPedidoPorID_DeveRetornarBadRequestQuandoIDPedidoNaoEncontradoException() throws IDPedidoNaoEncontradoException, JsonProcessingException {
   // Dados de entrada
   Long idPedido = 123L;

   // Mock do serviço lançando exceção
   when(pedidoServico.buscarPedidoPorId(eq(idPedido))).thenThrow(new IDPedidoNaoEncontradoException());

   // Execução do método
   ResponseEntity<?> responseEntity = pedidoController.buscarPedidoPorID(idPedido);

   // Verificações
   assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
   RequisicaoExcecao responseBody = (RequisicaoExcecao) responseEntity.getBody();

  }

  private Pedido criarPedido() {
   Pedido pedido = new Pedido(Arrays.asList(
           new ProdutoVO("1", "5.0"),
           new ProdutoVO("2", "3.0")
   ), "123", 10.0, StatusPedidoEnum.RECEBIDO, "1:00");
   return pedido;
  }

  private void assertPedidoRequisicaoEqualsPedido(Pedido pedido, PedidoRequisicao pedidoRequisicao) {
   assertEquals(pedido.getId().toString(), pedidoRequisicao.getIdPedido());
   assertEquals(pedido.getCliente(), pedidoRequisicao.getIdCliente());
   assertEquals(pedido.getValorTotal(), pedidoRequisicao.getValorTotal());
   assertEquals(pedido.getStatus().getNome(), pedidoRequisicao.getStatus());
   assertEquals(pedido.getTempoEspera(), pedidoRequisicao.getTempoEspera());

   List<ProdutosReq> produtosReq = pedido.getProdutos().stream()
           .map(produto -> ProdutosReq.builder()
                   .idProduto(produto.getIdProduto())
                   .quantidadeProduto(produto.getQuantidadeProduto())
                   .build())
           .collect(Collectors.toList());

   assertEquals(produtosReq, pedidoRequisicao.getProdutos());
  }

 }
