package br.com.appfastfood.entities;

import java.util.UUID;

public class Cliente {

    private UUID id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Cliente() {}

    public Cliente(String nome, String cpf, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Cliente(UUID id, String nome, String cpf, String telefone, String email) {
        this(nome, cpf, telefone, email);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
