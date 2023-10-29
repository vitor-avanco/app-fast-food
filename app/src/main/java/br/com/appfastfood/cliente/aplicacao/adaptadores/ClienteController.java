package br.com.appfastfood.cliente.aplicacao.adaptadores;


import br.com.appfastfood.cliente.aplicacao.adaptadores.requisicao.RequisicaoCliente;
import br.com.appfastfood.cliente.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import br.com.appfastfood.cliente.dominio.modelos.Cliente;
import br.com.appfastfood.cliente.dominio.servicos.portas.ClienteServico;
import br.com.appfastfood.cliente.exceptions.ClienteNaoEncontradoException;
import br.com.appfastfood.configuracoes.logs.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Clientes", description = "Cadastro do cliente")
public class ClienteController {
    private ClienteServico clienteServico;
    private Log logger;
    public ClienteController(ClienteServico clienteServico, Log logger) {
        this.clienteServico = clienteServico;
        this.logger = logger;
    }

    @Operation(summary = "Cadastrar Cliente",
            description = "Funcionalidade que realiza o cadastro do cliente a partir de seus dados pessoais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequisicaoCliente.class)) }),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequisicaoExcecao.class)))})
    @PostMapping("/clientes")
    public ResponseEntity<?> cadastrar(@RequestBody RequisicaoCliente requisicaoCliente) {
        try {
            UUID identificadorCliente = this.clienteServico.cadastrar(requisicaoCliente.getNome(), requisicaoCliente.getCpf(), requisicaoCliente.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequisicaoCliente(null, null, null, identificadorCliente.toString()));
        }catch (IllegalArgumentException e){
            RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            //logger.aviso(jsonExcecao.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
        }
    }

    @Operation(summary = "Consultar Cliente",
            description = "Funcionalidade que retorna os dados do cliente a partir de seu CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do cliente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequisicaoCliente.class)) }),
            @ApiResponse(responseCode = "404", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequisicaoExcecao.class)))})
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> buscarPorCpf(@PathVariable("id") String cpf) {
        try {
            Cliente cliente = this.clienteServico.buscarPorCpf(cpf);
            RequisicaoCliente clienteJson = new RequisicaoCliente(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), null);
            return ResponseEntity.status(HttpStatus.OK).body(clienteJson);
        }catch(ClienteNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequisicaoExcecao(e.getMessage(), HttpStatus.NOT_FOUND.value()));
        }
    }
}