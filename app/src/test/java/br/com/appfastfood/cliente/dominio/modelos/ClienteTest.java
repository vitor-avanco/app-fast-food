package br.com.appfastfood.cliente.dominio.modelos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void testConstrutorCliente_Valido() {
        // Arrange
        String nome = "João da Silva";
        String cpf = "12345678901";
        String email = "joao@example.com";

        // Act
        Cliente cliente = new Cliente(nome, cpf, email);

        // Assert
        Assertions.assertEquals(nome, cliente.getNome());
        Assertions.assertEquals(cpf, cliente.getCpf());
        Assertions.assertEquals(email, cliente.getEmail());
    }

    @Test
    void testConstrutorCliente_CamposVaziosOuNulos() {
        // Arrange
        String nome = "";
        String cpf = null;
        String email = "";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Cliente(nome, cpf, email));
    }
}
