package br.com.appfastfood.cliente.infraestrutura;

import br.com.appfastfood.cliente.infraestrutura.entidades.EntidadeCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataClienteRepository extends JpaRepository<EntidadeCliente, UUID> {
}