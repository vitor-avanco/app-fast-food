package br.com.appfastfood.interfaces.usecases;

import br.com.appfastfood.entities.Cliente;

import java.util.Optional;

public interface IdentificarClienteUseCasePort {
    Optional<Cliente> execute(String cpf);
}
