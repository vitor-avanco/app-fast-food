package br.com.appfastfood.jpa.entities;

import br.com.appfastfood.entities.Cliente;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "clientes")
public class ClienteJpaEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String cpf;
    private String telefone;

    private String email;

    public Cliente toDomain() {
        return new Cliente(id, nome, cpf, telefone, email);
    }

    public static ClienteJpaEntity fromDomain (Cliente cliente) {
        ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity();

        clienteJpaEntity.setNome(cliente.getNome());
        clienteJpaEntity.setCpf(cliente.getCpf());
        clienteJpaEntity.setTelefone(cliente.getTelefone());
        clienteJpaEntity.setEmail(cliente.getEmail());

        return clienteJpaEntity;
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

    public void setEmail(String email) {
        this.email = email;
    }
}
