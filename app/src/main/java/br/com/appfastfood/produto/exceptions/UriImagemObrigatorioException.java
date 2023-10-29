package br.com.appfastfood.produto.exceptions;

public class UriImagemObrigatorioException extends IllegalAccessError {

    public static String MESSAGER = "URI é um campo obrigatório!";

    public UriImagemObrigatorioException() {
        super(MESSAGER);
    }
}
