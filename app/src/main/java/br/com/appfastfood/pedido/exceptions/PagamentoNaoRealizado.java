package br.com.appfastfood.pedido.exceptions;

public class PagamentoNaoRealizado extends IllegalAccessError {
    public static String MESSAGER = "Pagamento não foi aprovado.";
    public PagamentoNaoRealizado() { super(MESSAGER); }
}
