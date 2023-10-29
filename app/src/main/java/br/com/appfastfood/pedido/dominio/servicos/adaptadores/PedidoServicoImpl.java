package br.com.appfastfood.pedido.dominio.servicos.adaptadores;

import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.VO.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.dominio.servicos.portas.PedidoServico;
import br.com.appfastfood.pedido.exceptions.IDPedidoNaoEncontradoException;
import br.com.appfastfood.pedido.exceptions.PedidoJaFinalizadoException;
import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.servicos.portas.ProdutoServico;

import java.util.ArrayList;
import java.util.List;


public class PedidoServicoImpl implements PedidoServico{

    private final ProdutoServico produtoServicoImplInject;
    private final PedidoRepositorio pedidoRepositorio;

    public PedidoServicoImpl(PedidoRepositorio pedidoRepositorio, ProdutoServico produtoServicoImplInject ) {

        this.pedidoRepositorio = pedidoRepositorio;
        this.produtoServicoImplInject= produtoServicoImplInject;
    }

    @Override
    public String criar(PedidoRequisicao pedidoReq, String status, String tempo) {
        Produto produtoBuscaId = null;
        Double valorTotal = 0.0;
        List<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
        for(ProdutosReq produtoReq : pedidoReq.getProdutos()){
            ProdutoVO produtoVO = new ProdutoVO(produtoReq.getIdProduto(), produtoReq.getQuantidadeProduto());
            produtoBuscaId = produtoServicoImplInject.buscaProdutoPorId(Long.valueOf(produtoReq.getIdProduto()));
            valorTotal += produtoBuscaId.getPreco().getPreco() * Double.parseDouble(produtoReq.getQuantidadeProduto());
            produtosVO.add(produtoVO);
        }
        Pedido pedido = new Pedido(produtosVO, pedidoReq.getIdCliente(), valorTotal, StatusPedidoEnum.buscaEnumPorStatusString(status), tempo);

        return this.pedidoRepositorio.criar(pedido);

    }
    @Override
    public Pedido atualizar(Long id) {
        Pedido pedidoBusca =  this.pedidoRepositorio.buscarPedidoPorId(id);
        Pedido pedidoAtualizado = null;
        if(pedidoBusca == null){
            throw new IDPedidoNaoEncontradoException();
        }

        if(pedidoBusca.getStatus() == StatusPedidoEnum.RECEBIDO){
            pedidoAtualizado = new Pedido(pedidoBusca.getId(),pedidoBusca.getProdutos(), pedidoBusca.getCliente(), pedidoBusca.getValorTotal(), StatusPedidoEnum.EM_PREPARACAO, pedidoBusca.getTempoEspera());
        }else if(pedidoBusca.getStatus()  == StatusPedidoEnum.EM_PREPARACAO){
            pedidoAtualizado = new Pedido(pedidoBusca.getId(),pedidoBusca.getProdutos(), pedidoBusca.getCliente(), pedidoBusca.getValorTotal(), StatusPedidoEnum.PRONTO, pedidoBusca.getTempoEspera());
        }else if(pedidoBusca.getStatus()  == StatusPedidoEnum.PRONTO){
            pedidoAtualizado = new Pedido(pedidoBusca.getId(),pedidoBusca.getProdutos(), pedidoBusca.getCliente(), pedidoBusca.getValorTotal(), StatusPedidoEnum.FINALIZADO, pedidoBusca.getTempoEspera());
        }else if (pedidoBusca.getStatus()  == StatusPedidoEnum.FINALIZADO){
            throw new PedidoJaFinalizadoException();
        }

        return this.pedidoRepositorio.atualizar(pedidoAtualizado);
    }

    @Override
    public Pedido buscarPedidoPorId(Long id) {
       return this.pedidoRepositorio.buscarPedidoPorId(id);
    }
    
    @Override
    public List<Pedido> listarTodosPedidos() {
        return this.pedidoRepositorio.listarTodosOsPedidos();
    }

}
