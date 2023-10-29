package br.com.appfastfood.cliente.dominio.VO;

import br.com.appfastfood.cliente.dominio.modelos.Validacoes;

public class Cpf {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public Cpf(String cpf) {
        validarCpf(cpf);
        this.cpf = cpf;
    }

    private void validarCpf(String cpf) {
        if (!Validacoes.validaPadraoRegex(cpf, "^[0-9]{11}$")) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

}