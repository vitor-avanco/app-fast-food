package br.com.appfastfood.cliente.dominio.modelos;

import br.com.appfastfood.cliente.dominio.VO.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailTest {

    @Test
    void testConstrutorEmail_Valido() {
        // Arrange
        String email = "joao@example.com";

        // Act
        Email emailObj = new Email(email);

        // Assert
        Assertions.assertEquals(email, emailObj.getEmail());
    }

    @Test
    void testConstrutorEmail_Invalido() {
        // Arrange
        String email = "joao@";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Email(email));
    }
}
