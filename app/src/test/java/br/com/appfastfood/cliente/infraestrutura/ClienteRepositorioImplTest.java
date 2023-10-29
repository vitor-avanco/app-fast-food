package br.com.appfastfood.cliente.infraestrutura;

import br.com.appfastfood.cliente.dominio.modelos.Cliente;
import br.com.appfastfood.cliente.infraestrutura.entidades.EntidadeCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.DigestUtils;

import java.util.Optional;
import java.util.UUID;

class ClienteRepositorioImplTest {

    @Mock
    private SpringDataClienteRepository clienteRepository;

    private ClienteRepositorioImpl clienteRepositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteRepositorio = new ClienteRepositorioImpl(clienteRepository);
    }

    @Test
    void testBuscarPorCpf_ClienteEncontrado() {
        // Arrange
        String cpf = "12345678901";
        UUID id = UUID.nameUUIDFromBytes(DigestUtils.md5Digest(cpf.getBytes()));
        EntidadeCliente entidadeCliente = new EntidadeCliente("João da Silva", cpf, "joao@example.com");
        Mockito.when(clienteRepository.findById(id)).thenReturn(Optional.of(entidadeCliente));

        // Act
        Optional<EntidadeCliente> resultado = clienteRepositorio.buscarPorCpf(cpf);

        // Assert
        Assertions.assertTrue(resultado.isPresent());
        Assertions.assertEquals(entidadeCliente, resultado.get());
    }

    @Test
    void testBuscarPorCpf_ClienteNaoEncontrado() {
        // Arrange
        String cpf = "12345678901";
        UUID id = UUID.nameUUIDFromBytes(DigestUtils.md5Digest(cpf.getBytes()));
        Mockito.when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<EntidadeCliente> resultado = clienteRepositorio.buscarPorCpf(cpf);

        // Assert
        Assertions.assertFalse(resultado.isPresent());
    }

    @Test
    void testCadastrar() {
        // Arrange
        String nome = "João da Silva";
        String cpf = "12345678901";
        String email = "joao@example.com";
        Cliente cliente = new Cliente(nome, cpf, email);
        EntidadeCliente entidadeCliente = new EntidadeCliente(nome, cpf, email);
        Mockito.when(clienteRepository.save(entidadeCliente)).thenReturn(entidadeCliente);

        // Act
        UUID resultado = clienteRepositorio.cadastrar(cliente);

        // Assert
        Assertions.assertNotNull(resultado);
    }
}
