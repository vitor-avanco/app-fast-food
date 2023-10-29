package br.com.appfastfood.cliente.dominio.VO;

import br.com.appfastfood.cliente.dominio.modelos.Validacoes;

public class Nome {
    private String nome;
    public Nome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    private void validarNome(String nome) {
        if(Validacoes.validaTamanhoMaximoDoCampo(nome, 30)){
            throw new IllegalArgumentException("Nome invalido!");
        }
    }


    public String getNome() {
        return nome;
    }

}