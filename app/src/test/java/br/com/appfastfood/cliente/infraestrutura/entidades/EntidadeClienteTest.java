package br.com.appfastfood.cliente.infraestrutura.entidades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntidadeClienteTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange

        String nome = "João da Silva";
        String cpf = "12345678901";
        String email = "joao@example.com";

        // Act
        EntidadeCliente entidadeCliente = new EntidadeCliente(nome, cpf, email);

        // Assert

        Assertions.assertEquals(nome, entidadeCliente.getNome());
        Assertions.assertEquals(cpf, entidadeCliente.getCpf());
        Assertions.assertEquals(email, entidadeCliente.getEmail());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        String cpf1 = "12345678901";
        String cpf2 = "12345678902";

        EntidadeCliente entidadeCliente1 = new EntidadeCliente("João da Silva", cpf1, "joao@example.com");
        EntidadeCliente entidadeCliente2 = new EntidadeCliente("Maria Souza", cpf2, "maria@example.com");


        // Assert
        Assertions.assertEquals(entidadeCliente1, entidadeCliente1);
        Assertions.assertEquals(entidadeCliente1.hashCode(), entidadeCliente1.hashCode());

        Assertions.assertNotEquals(entidadeCliente1, entidadeCliente2);
        Assertions.assertNotEquals(entidadeCliente1.hashCode(), entidadeCliente2.hashCode());

    }
}
