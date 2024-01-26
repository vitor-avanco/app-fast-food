package br.com.appfastfood.interfaces.usecases;
import br.com.appfastfood.entities.Produto;

public interface CadastrarProdutoUseCasePort {
    Produto execute(Produto produto);
}
