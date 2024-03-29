package br.com.appfastfood.jpa.repositories;

import br.com.appfastfood.jpa.entities.ClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<ClienteJpaEntity, UUID> {
    List<ClienteJpaEntity> findByCpf(String cpf);
}
