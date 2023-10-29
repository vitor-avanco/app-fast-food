package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.produto.exceptions.UriImagemFormatoInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class UriImagemTest {

    @Test
    public void testConstrutorComUriImagemValida() {
        String uriImagem = "https://example.com/image.jpg";
        UriImagem uriImagemObjeto = new UriImagem(uriImagem);
        assertEquals(uriImagem, uriImagemObjeto.getUriImagem());
    }

    @Test
    public void testConstrutorComUriImagemInvalida() {
        String uriImagem = "invalid-url";
        assertThrows(UriImagemFormatoInvalidoException.class, () -> new UriImagem(uriImagem));
    }
}
