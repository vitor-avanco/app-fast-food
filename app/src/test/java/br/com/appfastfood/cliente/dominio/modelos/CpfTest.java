package br.com.appfastfood.cliente.dominio.modelos;

import br.com.appfastfood.cliente.dominio.VO.Cpf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CpfTest {

    @Test
    void testConstrutorCpf_Valido() {
        // Arrange
        String cpf = "12345678901";

        // Act
        Cpf cpfObj = new Cpf(cpf);

        // Assert
        Assertions.assertEquals(cpf, cpfObj.getCpf());
    }

    @Test
    void testConstrutorCpf_Invalido() {
        // Arrange
        String cpf = "123";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Cpf(cpf));
    }
}
