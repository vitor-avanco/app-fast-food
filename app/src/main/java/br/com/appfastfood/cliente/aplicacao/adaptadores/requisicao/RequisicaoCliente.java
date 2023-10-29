package br.com.appfastfood.cliente.aplicacao.adaptadores.requisicao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequisicaoCliente {

    @JsonProperty("uuid_cliente")
    private String uuid;
    private String nome;
    private String cpf;
    private String email;

    public RequisicaoCliente(String nome, String cpf, String email, String uuid) {
        this.uuid = uuid;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }
}
