package br.com.appfastfood.interfaces.usecases;

import br.com.appfastfood.entities.Produto;

public interface EditarProdutoUseCasePort {
    Produto execute(Produto produto);
}
