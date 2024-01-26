package br.com.appfastfood.usecases;

import br.com.appfastfood.entities.Cliente;
import br.com.appfastfood.interfaces.usecases.CadastrarClienteUseCasePort;
import br.com.appfastfood.interfaces.gateways.ClienteGatewayPort;

public class CadastrarClienteUseCase implements CadastrarClienteUseCasePort {
    private final ClienteGatewayPort clienteGateway;

    public CadastrarClienteUseCase(ClienteGatewayPort clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente execute(Cliente cliente) {

        clienteGateway.identificaPor(cliente.getCpf()).ifPresent(clienteExistente -> {
            throw new RuntimeException("Cliente já cadastrado");
        });

        return clienteGateway.cadastra(cliente);
    }
}
