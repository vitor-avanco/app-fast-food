package br.com.appfastfood.cliente.aplicacao.adaptadores;

import br.com.appfastfood.cliente.aplicacao.adaptadores.requisicao.RequisicaoCliente;
import br.com.appfastfood.cliente.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import br.com.appfastfood.cliente.dominio.modelos.Cliente;
import br.com.appfastfood.cliente.dominio.servicos.portas.ClienteServico;
import br.com.appfastfood.cliente.exceptions.ClienteNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ClienteControllerTest {

    @Autowired
    private ClienteController clienteController;


    @MockBean
    private ClienteServico clienteServico;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cadastrar_DeveRetornarStatusCreated_QuandoCadastrarComSucesso() {
        // Arrange
        RequisicaoCliente requisicaoCliente = new RequisicaoCliente("João", "12345678901", "joao@example.com", null);
        when(clienteServico.cadastrar(requisicaoCliente.getNome(), requisicaoCliente.getCpf(),
                requisicaoCliente.getEmail())).thenReturn(UUID.randomUUID());


        // Act
        ResponseEntity<?> responseEntity = clienteController.cadastrar(requisicaoCliente);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void cadastrar_DeveRetornarStatusBadRequest_QuandoCadastrarComDadosInvalidos() {
        // Arrange
        RequisicaoCliente requisicaoCliente = new RequisicaoCliente("João", null, "joao@example.com", null);
        when(clienteServico.cadastrar(requisicaoCliente.getNome(),
                requisicaoCliente.getCpf(),
                requisicaoCliente.getEmail())).thenThrow(new IllegalArgumentException("a"));
        // Act
        ResponseEntity<?> responseEntity = clienteController.cadastrar(requisicaoCliente);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody() instanceof RequisicaoExcecao);
        RequisicaoExcecao excecao = (RequisicaoExcecao) responseEntity.getBody();
        assertEquals("a", excecao.getMensagem());
    }

    @Test
    public void buscarPorCpf_DeveRetornarStatusOk_QuandoClienteEncontrado() {
        // Arrange
        String cpf = "12345678901";
        Cliente cliente = new Cliente("Joao", cpf, "email@email.com");
        when(clienteServico.buscarPorCpf(cpf)).thenReturn(cliente);

        // Act
        ResponseEntity<?> responseEntity = clienteController.buscarPorCpf(cpf);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody() instanceof RequisicaoCliente);
        RequisicaoCliente clienteJson = (RequisicaoCliente) responseEntity.getBody();
        assertEquals(cliente.getNome(), clienteJson.getNome());
        assertEquals(cliente.getCpf(), clienteJson.getCpf());
        assertEquals(cliente.getEmail(), clienteJson.getEmail());
    }

    @Test
    public void buscarPorCpf_DeveRetornarStatusNotFound_QuandoClienteNaoEncontrado() {
        // Arrange
        String cpf = "12345678901";
        when(clienteServico.buscarPorCpf(cpf)).thenThrow(new ClienteNaoEncontradoException());

        // Act
        ResponseEntity<?> responseEntity = clienteController.buscarPorCpf(cpf);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(true,responseEntity.getBody() instanceof RequisicaoExcecao);
        RequisicaoExcecao excecao = (RequisicaoExcecao) responseEntity.getBody();
        assertEquals("Não foi possível encontrar cliente!", excecao.getMensagem());
    }
}