package br.com.appfastfood.interfaces.usecases;
import br.com.appfastfood.entities.Produto;
import br.com.appfastfood.entities.TipoDeProduto;

import java.util.List;

public interface ObterProdutosPorCategoriaUseCasePort {
    List<Produto> execute(TipoDeProduto categoria);
}
