package br.com.appfastfood.pedido.infraestrutura;

import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.VO.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.exceptions.IDPedidoNaoEncontradoException;
import br.com.appfastfood.pedido.exceptions.PagamentoNaoRealizado;
import br.com.appfastfood.pedido.infraestrutura.entidades.PedidoEntidade;
import br.com.appfastfood.pedido.infraestrutura.entidades.ProdEnt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PedidoRepositorioImpl implements PedidoRepositorio {

    private final SpringDataPedidoRepository springDataPedidoRepository;

    public PedidoRepositorioImpl(SpringDataPedidoRepository springDataPedidoRepository) {
        this.springDataPedidoRepository = springDataPedidoRepository;
    }

    @Override
    public String criar(Pedido pedido) {
        Double valorTotal = 0D;
        List<ProdEnt> produtosEntidade = new ArrayList<>();
        pedido.getProdutos().forEach(produto -> {
           produtosEntidade.add(new ProdEnt(produto.getIdProduto(), produto.getQuantidadeProduto()));
        });
        PedidoEntidade pedidoDb = new PedidoEntidade(produtosEntidade, pedido.getCliente(), pedido.getValorTotal(), StatusPedidoEnum.retornaNomeEnum(pedido.getStatus()), pedido.getTempoEspera());

        if (realizarPagamento()){
            springDataPedidoRepository.save(pedidoDb);
        }else{
            throw new PagamentoNaoRealizado();
        }
        return pedidoDb.getId().toString();
    }

    @Override
    public Pedido atualizar(Pedido pedido) {
        List<ProdEnt> produtosEntidade = new ArrayList<>();
        pedido.getProdutos().forEach(produto -> {
            produtosEntidade.add(new ProdEnt(produto.getIdProduto(), produto.getQuantidadeProduto()));
        });
        PedidoEntidade pedidoDb = new PedidoEntidade(pedido.getId(),produtosEntidade, pedido.getCliente(), pedido.getValorTotal(), StatusPedidoEnum.retornaNomeEnum(pedido.getStatus()), pedido.getTempoEspera());
        this.springDataPedidoRepository.save(pedidoDb);
        return pedido;
    }

    @Override
    public List<Pedido> listarTodosOsPedidos() {
       List<PedidoEntidade> pedido = this.springDataPedidoRepository.findAll();
        if(pedido.isEmpty()){
            throw new IDPedidoNaoEncontradoException();
        }
        List<Pedido> pedidosRetorno = new ArrayList<>();
        pedido.forEach(pedidoEntidade -> {
            List<ProdutoVO> produtosVO = pedidoEntidade.getProdutos().stream()
                    .map(prodEnt -> new ProdutoVO(prodEnt.getIdProduto(), prodEnt.getQuantidadeProduto()))
                    .collect(Collectors.toList());

            Pedido pedidoRetorno = new Pedido(pedidoEntidade.getId(), produtosVO, pedidoEntidade.getClienteId(),
                    pedidoEntidade.getValorTotal(),
                    StatusPedidoEnum.buscaEnumPorStatusString(pedidoEntidade.getStatus()),
                    pedidoEntidade.getTempoEspera());
            pedidosRetorno.add(pedidoRetorno);
        });
        return pedidosRetorno;
    }

    @Override
    public Pedido buscarPedidoPorId(Long id) {

        Optional<PedidoEntidade> pedidoEntidadeBusca = this.springDataPedidoRepository.findById(id);
        if (!pedidoEntidadeBusca.isPresent()){
            throw new IDPedidoNaoEncontradoException();
        }
        List<ProdutoVO> produtosVO = pedidoEntidadeBusca.get().getProdutos().stream()
                .map(prodEnt -> new ProdutoVO(prodEnt.getIdProduto(), prodEnt.getQuantidadeProduto()))
                .collect(Collectors.toList());

        Pedido pedidoRetorno = new Pedido(pedidoEntidadeBusca.get().getId(), produtosVO, pedidoEntidadeBusca.get().getClienteId(),
                pedidoEntidadeBusca.get().getValorTotal(),
                StatusPedidoEnum.buscaEnumPorStatusString(pedidoEntidadeBusca.get().getStatus()),
                pedidoEntidadeBusca.get().getTempoEspera());
        return pedidoRetorno;
    }

    @Override
    public Boolean realizarPagamento(){
        return true;
    }
   
}
