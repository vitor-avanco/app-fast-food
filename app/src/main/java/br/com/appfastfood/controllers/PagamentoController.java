package br.com.appfastfood.controllers;

import br.com.appfastfood.usecases.model.ComandoDeConfirmacaoDePagamento;
import br.com.appfastfood.interfaces.usecases.ConsultarStatusPagamentoUseCasePort;
import br.com.appfastfood.interfaces.usecases.ReceberConfirmacaoPagamentoUseCasePort;
import br.com.appfastfood.presenters.PagamentoPresenter;

import java.util.UUID;

public class PagamentoController {

    private final ConsultarStatusPagamentoUseCasePort consultarStatusPagamentoUseCase;
    private final ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase;

    public PagamentoController(ConsultarStatusPagamentoUseCasePort consultarStatusPagamentoUseCase,
                               ReceberConfirmacaoPagamentoUseCasePort receberConfirmacaoPagamentoUseCase) {

        this.consultarStatusPagamentoUseCase = consultarStatusPagamentoUseCase;
        this.receberConfirmacaoPagamentoUseCase = receberConfirmacaoPagamentoUseCase;
    }

    public PagamentoPresenter consultarStatusPagamento(UUID pedidoId) {
        Boolean pagamentoAprovado = consultarStatusPagamentoUseCase.execute(pedidoId);
        return new PagamentoPresenter(pagamentoAprovado);
    }

    public String receberConfirmacaoPagamento(ComandoDeConfirmacaoDePagamento comandoDeConfirmacaoDePagamento) {
        return receberConfirmacaoPagamentoUseCase.execute(comandoDeConfirmacaoDePagamento);
    }
}
