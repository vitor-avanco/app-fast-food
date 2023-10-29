package br.com.appfastfood.cliente.exceptions; 
 
public class ClienteNaoEncontradoException extends RuntimeException {
    public static String MESSAGER = "Não foi possível encontrar cliente!";

    public ClienteNaoEncontradoException() { super(MESSAGER); }}
