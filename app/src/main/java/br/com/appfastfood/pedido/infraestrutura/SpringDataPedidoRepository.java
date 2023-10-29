package br.com.appfastfood.pedido.infraestrutura;

import br.com.appfastfood.pedido.infraestrutura.entidades.PedidoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpringDataPedidoRepository extends JpaRepository<PedidoEntidade, Long> {
     PedidoEntidade findPedidoEntidadeById(Long id);
   
}
