package br.com.appfastfood.produto.exceptions;

public class IDNaoEncontradoException extends IllegalAccessError {
    public static String MESSAGER = "Id Produto não encontrado!";
    public IDNaoEncontradoException() { super(MESSAGER); }
}
