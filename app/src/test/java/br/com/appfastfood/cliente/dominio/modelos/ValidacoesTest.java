package br.com.appfastfood.cliente.dominio.modelos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidacoesTest {

    @Test
    void testValidaTamanhoMaximoDoCampo_TamanhoMenor() {
        // Arrange
        String campo = "1234567890";
        int tamanhoMaximo = 15;

        // Act
        boolean resultado = Validacoes.validaTamanhoMaximoDoCampo(campo, tamanhoMaximo);

        // Assert
        Assertions.assertFalse(resultado);
    }

    // Demais testes omitidos por brevidade

    @Test
    void testValidaCamposVaziosOuNulos_CamposPreenchidos() {
        // Arrange
        String nome = "João da Silva";
        String cpf = "12345678901";
        String email = "joao@example.com";

        // Act
        boolean resultado = Validacoes.validaCamposVaziosOuNulos(nome, cpf, email);

        // Assert
        Assertions.assertFalse(resultado);
    }

    @Test
    void testValidaCamposVaziosOuNulos_CamposVazios() {
        // Arrange
        String nome = "";
        String cpf = "";
        String email = "";

        // Act
        boolean resultado = Validacoes.validaCamposVaziosOuNulos(nome, cpf, email);

        // Assert
        Assertions.assertTrue(resultado);
    }

    @Test
    void testValidaCamposVaziosOuNulos_CamposNulos() {
        // Arrange
        String nome = null;
        String cpf = null;
        String email = null;

        // Act
        boolean resultado = Validacoes.validaCamposVaziosOuNulos(nome, cpf, email);

        // Assert
        Assertions.assertTrue(resultado);
    }

    @Test
    void testValidaCamposVaziosOuNulos_CampoVazio() {
        // Arrange
        String campo = "";

        // Act
        boolean resultado = Validacoes.validaCamposVaziosOuNulos(campo, "", "");

        // Assert
        Assertions.assertTrue(resultado);
    }

    @Test
    void testValidaCamposVaziosOuNulos_CampoNulo() {
        // Arrange
        String campo = null;

        // Act
        boolean resultado = Validacoes.validaCamposVaziosOuNulos(campo, "", "");

        // Assert
        Assertions.assertTrue(resultado);
    }
}
