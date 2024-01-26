package br.com.appfastfood.controllers;

import br.com.appfastfood.entities.Produto;
import br.com.appfastfood.entities.TipoDeProduto;
import br.com.appfastfood.interfaces.usecases.CadastrarProdutoUseCasePort;
import br.com.appfastfood.interfaces.usecases.EditarProdutoUseCasePort;
import br.com.appfastfood.interfaces.usecases.ExcluirProdutoUseCasePort;
import br.com.appfastfood.interfaces.usecases.ObterProdutosPorCategoriaUseCasePort;
import br.com.appfastfood.presenters.ProdutoPresenter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProdutoController {

    private final ObterProdutosPorCategoriaUseCasePort obterProdutosPorCategoriaUseCase;
    private final CadastrarProdutoUseCasePort cadastrarProdutoUseCase;
    private final EditarProdutoUseCasePort editarProdutoUseCase;

    private final ExcluirProdutoUseCasePort excluirProdutoUseCase;

    public ProdutoController(CadastrarProdutoUseCasePort cadastrarProdutoUseCase,
                      EditarProdutoUseCasePort editarProdutoUseCase,
                      ExcluirProdutoUseCasePort excluirProdutoUseCase,
                      ObterProdutosPorCategoriaUseCasePort obterProdutosPorCategoriaUseCase) {
        this.cadastrarProdutoUseCase = cadastrarProdutoUseCase;
        this.editarProdutoUseCase = editarProdutoUseCase;
        this.excluirProdutoUseCase = excluirProdutoUseCase;
        this.obterProdutosPorCategoriaUseCase = obterProdutosPorCategoriaUseCase;
    }

    public List<ProdutoPresenter> obterProdutosPorCategoria(String categoria) {
        List<Produto> produtos = obterProdutosPorCategoriaUseCase.execute(TipoDeProduto.valueOf(categoria));
        return produtos.stream().map(ProdutoPresenter::fromDomain).collect(Collectors.toList());
    }

    public ProdutoPresenter cadastrarProduto(Produto produto) {
        return ProdutoPresenter.fromDomain(cadastrarProdutoUseCase.execute(produto));
    }

    public ProdutoPresenter editarProduto(Produto produto) {
        return ProdutoPresenter.fromDomain(editarProdutoUseCase.execute(produto));
    }

    public void excluirProduto(UUID id) {
        excluirProdutoUseCase.execute(id);
    }
}
