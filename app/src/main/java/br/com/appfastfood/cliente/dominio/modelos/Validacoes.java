package br.com.appfastfood.cliente.dominio.modelos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacoes {
    public static boolean validaTamanhoMaximoDoCampo(String campo, int i) {
        return campo.length() > i;
    }

    public static boolean validaPadraoRegex(String campo, String s) {
        Matcher matcher = Pattern.compile(s).matcher(campo);
        return matcher.matches();
    }

    public static boolean validaCamposVaziosOuNulos(String nome, String cpf, String email) {
        return estaBrancoOuNulo(nome) && estaBrancoOuNulo(cpf) && estaBrancoOuNulo(email);
    }

    private static boolean estaBrancoOuNulo(String campo) {
        return campo == null || campo.isBlank();
    }
}
