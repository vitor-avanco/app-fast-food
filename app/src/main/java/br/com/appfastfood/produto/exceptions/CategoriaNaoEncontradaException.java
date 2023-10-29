package br.com.appfastfood.produto.exceptions;

public class CategoriaNaoEncontradaException extends IllegalAccessError {
    public static String MESSAGER = "Categoria não existente!";
    public CategoriaNaoEncontradaException() {
        super(MESSAGER);
    }
}
