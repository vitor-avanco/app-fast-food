package br.com.appfastfood.presenters;

public class PagamentoPresenter {

    private final Boolean pagamentoAprovado;

    public PagamentoPresenter(Boolean pagamentoAprovado) {

        this.pagamentoAprovado = pagamentoAprovado;
    }

    public boolean isPagamentoAprovado() {
        return pagamentoAprovado;
    }
}
