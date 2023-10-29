package br.com.appfastfood.cliente.dominio.repositorios;

import br.com.appfastfood.cliente.dominio.modelos.Cliente;
import br.com.appfastfood.cliente.infraestrutura.entidades.EntidadeCliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepositorio {
    Optional<EntidadeCliente> buscarPorCpf(String cpf);
    UUID cadastrar(Cliente cliente);
}
