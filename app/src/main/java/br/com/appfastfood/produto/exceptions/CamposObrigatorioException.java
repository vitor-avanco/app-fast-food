package br.com.appfastfood.produto.exceptions;

public class CamposObrigatorioException extends IllegalAccessError {
    public static String MESSAGER = "Todos os campos são obrigatórios!";
    public CamposObrigatorioException() {
        super(MESSAGER);
    }
}
