package com.template.validacao;

import java.util.Set;
import static com.template.util.DialogUtil.*;

public class GeneroValidacao implements Validacao<String> {

    private final String generoInserido;

    private static final Set<String> generos_validos = Set.of(
            "Ação", "Aventura", "Comédia", "Drama", "Terror",
            "Ficção Científica", "Romance", "Animação", "Documentário"
    );

    public GeneroValidacao(String generoInput) {
        this.generoInserido = generoInput;
    }

    @Override
    public boolean validar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            showWarning("O gênero não pode ser vazio.");
            return false;
        }

        String generoNormalizado = primeiraLetraMaiuscula(valor);

        if (!generos_validos.contains(generoNormalizado)) {
            return false;
        }

        return true;
    }

    private String primeiraLetraMaiuscula(String texto) {
        String primeiraMaiuscula = texto.trim().toLowerCase();
        if (primeiraMaiuscula.isEmpty()) return primeiraMaiuscula;

        String[] palavras = primeiraMaiuscula.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                sb.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1))
                        .append(" ");
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String getMensagemErro() {
        return "Gênero inválido. Escolha um gênero de filme válido.";
    }

    @Override
    public String getValor() {
        return generoInserido;
    }
}