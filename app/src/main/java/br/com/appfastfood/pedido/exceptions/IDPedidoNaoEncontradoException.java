package br.com.appfastfood.pedido.exceptions;

public class IDPedidoNaoEncontradoException extends IllegalAccessError {
    public static String MESSAGER = "Pedido não encontrado!";
    public IDPedidoNaoEncontradoException() { super(MESSAGER); }
}
