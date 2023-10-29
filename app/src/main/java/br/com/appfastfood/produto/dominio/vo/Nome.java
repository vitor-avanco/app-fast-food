package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.produto.exceptions.CamposObrigatorioException;

public class Nome {
    private String nome;
    public Nome(String nome) {
        this.isValid(nome);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    private void isValid(String nome) {
        if(nome == null || nome.isEmpty()) {
            throw new CamposObrigatorioException();
        }
    }


}
