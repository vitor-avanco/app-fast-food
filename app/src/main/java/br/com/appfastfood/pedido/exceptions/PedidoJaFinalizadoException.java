package br.com.appfastfood.pedido.exceptions;

public class PedidoJaFinalizadoException extends IllegalAccessError {
    public static String MESSAGER = "Pedido já está finalizado! Não é possível alterar seu status.";
    public PedidoJaFinalizadoException() { super(MESSAGER); }
}
