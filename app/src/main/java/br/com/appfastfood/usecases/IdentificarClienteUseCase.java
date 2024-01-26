package br.com.appfastfood.usecases;

import br.com.appfastfood.entities.Cliente;
import br.com.appfastfood.interfaces.gateways.ClienteGatewayPort;
import br.com.appfastfood.interfaces.usecases.IdentificarClienteUseCasePort;

import java.util.Optional;

public class IdentificarClienteUseCase implements IdentificarClienteUseCasePort {
    private final ClienteGatewayPort clienteGateway;

    public IdentificarClienteUseCase(ClienteGatewayPort clienteGateway) {

        this.clienteGateway = clienteGateway;
    }
    @Override
    public Optional<Cliente> execute(String cpf) {
        return clienteGateway.identificaPor(cpf);
    }
}
