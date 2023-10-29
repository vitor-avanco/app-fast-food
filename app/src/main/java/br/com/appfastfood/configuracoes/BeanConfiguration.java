package br.com.appfastfood.configuracoes;

import br.com.appfastfood.AppFastfoodApplication;
import br.com.appfastfood.cliente.dominio.repositorios.ClienteRepositorio;
import br.com.appfastfood.cliente.dominio.servicos.adaptadores.ClienteServicoImpl;
import br.com.appfastfood.cliente.dominio.servicos.portas.ClienteServico;
import br.com.appfastfood.configuracoes.logs.Log;
import br.com.appfastfood.configuracoes.logs.Log4jLog;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.dominio.servicos.adaptadores.PedidoServicoImpl;
import br.com.appfastfood.pedido.dominio.servicos.portas.PedidoServico;
import br.com.appfastfood.produto.dominio.repositorios.ProdutoRepositorio;
import br.com.appfastfood.produto.dominio.servicos.adaptadores.ProdutoServicoImpl;
import br.com.appfastfood.produto.dominio.servicos.portas.ProdutoServico;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AppFastfoodApplication.class)
public class BeanConfiguration {

    @Bean
    ClienteServico clienteServico(final ClienteRepositorio clienteRepositorio){
        return new ClienteServicoImpl(clienteRepositorio);
    }
    @Bean
    ProdutoServico produtoServico(ProdutoRepositorio produtoRepositorio){
        return new ProdutoServicoImpl(produtoRepositorio);
    }

    @Bean
    PedidoServico pedidoServico(PedidoRepositorio pedidoRepositorio, ProdutoServico produtoServico ){
        return new PedidoServicoImpl(pedidoRepositorio, produtoServico);
    }
    @Bean
    Log log(){
        return new Log4jLog(BeanConfiguration.class);
    }
}
