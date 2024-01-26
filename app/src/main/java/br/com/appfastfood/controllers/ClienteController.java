package br.com.appfastfood.controllers;

import br.com.appfastfood.exceptions.ClienteNaoEncontradoException;
import br.com.appfastfood.entities.Cliente;
import br.com.appfastfood.interfaces.usecases.CadastrarClienteUseCasePort;
import br.com.appfastfood.interfaces.usecases.IdentificarClienteUseCasePort;
import br.com.appfastfood.presenters.ClientePresenter;

public class ClienteController {

    private final CadastrarClienteUseCasePort cadastrarClienteUseCase;
    private final IdentificarClienteUseCasePort identificarClienteUseCase;

    public ClienteController(CadastrarClienteUseCasePort cadastrarClienteUseCase,
                             IdentificarClienteUseCasePort identificarClienteUseCase) {

        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.identificarClienteUseCase = identificarClienteUseCase;
    }

    public ClientePresenter identificarCliente(String cpf) {
        Cliente cliente = identificarClienteUseCase.execute(cpf).orElseThrow(() -> ClienteNaoEncontradoException.aPartirDoCpf(cpf));
        return ClientePresenter.fromDomain(cliente);
    }

    public ClientePresenter cadastrarCliente(Cliente cliente) {
        return ClientePresenter.fromDomain(cadastrarClienteUseCase.execute(cliente));
    }
}
