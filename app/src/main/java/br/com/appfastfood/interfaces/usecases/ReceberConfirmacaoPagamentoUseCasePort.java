package br.com.appfastfood.interfaces.usecases;

import br.com.appfastfood.usecases.model.ComandoDeConfirmacaoDePagamento;

public interface ReceberConfirmacaoPagamentoUseCasePort {

    String execute(ComandoDeConfirmacaoDePagamento comandoDeConfirmacaoDePagamento);
}
