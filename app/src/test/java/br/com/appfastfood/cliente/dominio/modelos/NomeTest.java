package br.com.appfastfood.cliente.dominio.modelos;

import br.com.appfastfood.cliente.dominio.VO.Nome;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NomeTest {

    @Test
    void testConstrutorNome_Valido() {
        // Arrange
        String nome = "João da Silva";

        // Act
        Nome nomeObj = new Nome(nome);

        // Assert
        Assertions.assertEquals(nome, nomeObj.getNome());
    }

    @Test
    void testConstrutorNome_Invalido() {
        // Arrange
        String nome = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Nome(nome));
    }
}
