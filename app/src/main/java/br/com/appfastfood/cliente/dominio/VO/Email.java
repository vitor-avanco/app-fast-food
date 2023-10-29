package br.com.appfastfood.cliente.dominio.VO;

import br.com.appfastfood.cliente.dominio.modelos.Validacoes;

public class Email {
    private String email;
    public String getEmail() {
        return email;
    }
    public Email(String email) {

        validarEmail(email);
        this.email = email;
    }

    private void validarEmail(String email) {
       if (!Validacoes.validaPadraoRegex(email, "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
           throw new IllegalArgumentException("Email inválido!");
       }
    }



}