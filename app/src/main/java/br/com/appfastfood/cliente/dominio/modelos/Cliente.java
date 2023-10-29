package br.com.appfastfood.cliente.dominio.modelos;

import br.com.appfastfood.cliente.dominio.VO.Cpf;
import br.com.appfastfood.cliente.dominio.VO.Email;
import br.com.appfastfood.cliente.dominio.VO.Nome;

public class Cliente {
    private Nome nome;
    private Cpf cpf;
    private Email email;

    public Cliente(String nome, String cpf, String email) {
        validarCampos(nome,cpf,email);
        this.nome = new Nome(nome);
        this.cpf = new Cpf(cpf);
        this.email = new Email(email);
    }

    private void validarCampos(String nome, String cpf, String email) {
        if(Validacoes.validaCamposVaziosOuNulos(nome,cpf,email)){
            throw new IllegalArgumentException("Todos os campos são obrigatórios!");
        }
    }

    public String getNome() {
        return nome.getNome();
    }

    public String getCpf() {
        return cpf.getCpf();
    }

    public String getEmail() {
        return email.getEmail();
    }


}
