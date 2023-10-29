package br.com.appfastfood.cliente.dominio.servicos;

import br.com.appfastfood.cliente.dominio.modelos.Cliente;
import br.com.appfastfood.cliente.dominio.repositorios.ClienteRepositorio;
import br.com.appfastfood.cliente.dominio.servicos.adaptadores.ClienteServicoImpl;
import br.com.appfastfood.cliente.exceptions.ClienteNaoEncontradoException;
import br.com.appfastfood.cliente.infraestrutura.ClienteRepositorioImpl;
import br.com.appfastfood.cliente.infraestrutura.SpringDataClienteRepository;
import br.com.appfastfood.cliente.infraestrutura.entidades.EntidadeCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

class ClienteServicoImplTest {

    @Mock
    private ClienteRepositorio clienteRepositorio;

    private ClienteServicoImpl clienteServico;
    @Mock
    private SpringDataClienteRepository clienteRepositoryMock;

// Configurar o comportamento do método save

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
         clienteRepositorio = new ClienteRepositorioImpl(clienteRepositoryMock);
        clienteServico = new ClienteServicoImpl(clienteRepositorio);
    }

    @Test
    void testBuscarPorCpf_ClienteEncontrado() {
        // Arrange
        String cpf = "12345678901";
        String nome = "João da Silva";
        String email = "joao@example.com";
        UUID id = UUID.randomUUID();
        EntidadeCliente entidadeCliente = new EntidadeCliente(nome, cpf, email);
        Mockito.when(clienteRepositorio.buscarPorCpf(cpf)).thenReturn(Optional.of(entidadeCliente));

        // Act
        Cliente resultado = clienteServico.buscarPorCpf(cpf);

        // Assert
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(nome, resultado.getNome());
        Assertions.assertEquals(cpf, resultado.getCpf());
        Assertions.assertEquals(email, resultado.getEmail());
    }

    @Test
    void testBuscarPorCpf_ClienteNaoEncontrado() {
        // Arrange
        String cpf = "12345678901";
        Mockito.when(clienteRepositorio.buscarPorCpf(cpf)).thenReturn(Optional.empty());

        // Act + Assert
        Assertions.assertThrows(ClienteNaoEncontradoException.class, () -> clienteServico.buscarPorCpf(cpf));
    }
}
