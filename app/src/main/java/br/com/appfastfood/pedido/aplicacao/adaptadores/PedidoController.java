package br.com.appfastfood.pedido.aplicacao.adaptadores;

import br.com.appfastfood.cliente.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.pedido.aplicacao.adaptadores.resposta.PedidoResposta;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.servicos.portas.PedidoServico;
import br.com.appfastfood.pedido.exceptions.IDPedidoNaoEncontradoException;
import br.com.appfastfood.pedido.exceptions.PagamentoNaoRealizado;
import br.com.appfastfood.pedido.exceptions.PedidoJaFinalizadoException;
import br.com.appfastfood.produto.exceptions.CategoriaNaoEncontradaException;
import br.com.appfastfood.produto.exceptions.IDNaoEncontradoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Tudo sobre pedidos")
public class PedidoController {
    private PedidoServico pedidoServico;

    public PedidoController(PedidoServico pedidoServico) {
        this.pedidoServico = pedidoServico;
    }

    @PostMapping
    @Operation(summary = "Cadastrar Pedido", description = "Funcionalidade de criar um pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "pedido cadastrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PedidoResposta.class)) }),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RequisicaoExcecao.class)))})
    public ResponseEntity<?> criar(@RequestBody PedidoRequisicao pedidoRequisicao){
       try {

         String id =   this.pedidoServico.criar(pedidoRequisicao,"RECEBIDO", "1:00");
         return ResponseEntity.status(HttpStatus.CREATED).body(PedidoRequisicao.builder().idPedido(id).build());

        } catch (IDNaoEncontradoException e) {
              RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
        }catch (PagamentoNaoRealizado e) {
              RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
        }

    }

    @PutMapping("/{id}")
     @Operation(summary = "Atualizar status do pedido", description = "Funcionalidade de atualizar o status do pedido passando o parametro 'id' do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = { @Content() }),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RequisicaoExcecao.class)))})
    public ResponseEntity<?> atualizarStatus(@PathVariable("id") Long id){
        try {
            Pedido pedidoRetorno = this.pedidoServico.atualizar(id);

            PedidoRequisicao pedidoResposta = PedidoRequisicao
                    .builder()
                    .produtos(pedidoRetorno.getProdutos().stream().map(produto ->
                            ProdutosReq.builder()
                                    .idProduto(produto.getIdProduto())
                                    .quantidadeProduto(produto.getQuantidadeProduto())
                                    .build()
                    ).collect(Collectors.toList()))
                    .idCliente(pedidoRetorno.getCliente())
                    .tempoEspera(pedidoRetorno.getTempoEspera())
                    .valorTotal(pedidoRetorno.getValorTotal())
                    .status(StatusPedidoEnum.retornaNomeEnum(pedidoRetorno.getStatus()))
                    .idPedido(pedidoRetorno.getId().toString()).build();

            return ResponseEntity.status(HttpStatus.OK).body(pedidoRetorno);
        } catch (IDPedidoNaoEncontradoException e) {
            RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
        }catch(PedidoJaFinalizadoException e){
            RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
        }
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedidos por id", description = "Funcionalidade que retorna o pedido passando o parametro id do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido filtrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class, subTypes = { PedidoRequisicao.class }))}),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RequisicaoExcecao.class)))})
    public ResponseEntity buscarPedidoPorID(@PathVariable(value = "id") Long id) throws JsonProcessingException {
       try {
            Pedido pedidoRetorno = this.pedidoServico.buscarPedidoPorId(id);

            PedidoRequisicao pedidoResposta = PedidoRequisicao
            .builder()
            .produtos(pedidoRetorno.getProdutos().stream().map(produto ->
                    ProdutosReq.builder()
                            .idProduto(produto.getIdProduto())
                            .quantidadeProduto(produto.getQuantidadeProduto())
                            .build()
            ).collect(Collectors.toList()))
            .idCliente(pedidoRetorno.getCliente())
            .tempoEspera(pedidoRetorno.getTempoEspera())
            .valorTotal(pedidoRetorno.getValorTotal())
            .status(StatusPedidoEnum.retornaNomeEnum(pedidoRetorno.getStatus()))
                    .idPedido(pedidoRetorno.getId().toString()).build();

            return ResponseEntity.status(HttpStatus.OK).body(pedidoResposta);
        } catch (IDPedidoNaoEncontradoException e) {
            RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
        }

    }

    @GetMapping
       @Operation(summary = "Buscar todos pedidos", description = "Funcionalidade que retorna todos pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos filtrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class, subTypes = { PedidoRequisicao.class }))}),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RequisicaoExcecao.class)))})    
    public ResponseEntity<Object> listarPedidos() throws JsonProcessingException{
      try {
         List<Pedido> pedidos = this.pedidoServico.listarTodosPedidos();
         List<PedidoRequisicao> pedidoRespostas = new ArrayList<>();
         pedidos.forEach(pedido -> {
                PedidoRequisicao pedidoResposta = PedidoRequisicao
                        .builder()
                        .produtos(pedido.getProdutos().stream().map(produto ->
                                ProdutosReq.builder()
                                        .idProduto(produto.getIdProduto())
                                        .quantidadeProduto(produto.getQuantidadeProduto())
                                        .build()
                        ).collect(Collectors.toList()))
                        .idCliente(pedido.getCliente())
                        .tempoEspera(pedido.getTempoEspera())
                        .valorTotal(pedido.getValorTotal())
                        .status(StatusPedidoEnum.retornaNomeEnum(pedido.getStatus()))
                        .idPedido(pedido.getId().toString()).build();
                pedidoRespostas.add(pedidoResposta);
            });

           return ResponseEntity.status(HttpStatus.OK).body(pedidoRespostas);
       } catch (IDPedidoNaoEncontradoException e) {
          RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
       }
    }
} 
