package br.com.appfastfood.cliente.dominio.servicos.portas;


import br.com.appfastfood.cliente.dominio.modelos.Cliente;

import java.util.UUID;

public interface ClienteServico {
    UUID cadastrar(String nome, String cpf, String email);
    Cliente buscarPorCpf(String cpf);

}
