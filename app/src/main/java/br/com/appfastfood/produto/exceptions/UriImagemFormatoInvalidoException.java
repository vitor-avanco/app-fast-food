package br.com.appfastfood.produto.exceptions;

public class UriImagemFormatoInvalidoException extends IllegalAccessError {

    public static String MESSAGER = "URI com formato inválido!";
    public UriImagemFormatoInvalidoException() {super(MESSAGER);}}
