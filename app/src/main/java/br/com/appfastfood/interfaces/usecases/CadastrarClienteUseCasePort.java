package br.com.appfastfood.interfaces.usecases;

import br.com.appfastfood.entities.Cliente;

public interface CadastrarClienteUseCasePort {
    Cliente execute(Cliente cliente);
}
