package com.template.validacao;

import static com.template.util.DialogUtil.*;

public class FilmeValidador {

    public static boolean validarFormulario(String nome, String genero, String anoLancamento, String bilheteria, String notaIMDB) {


        if (nome.isEmpty() || genero.isEmpty() || anoLancamento.isEmpty() || bilheteria.isEmpty() || notaIMDB.isEmpty()) {
            showWarning("Preencha todos os campos");
        return false;
    }
        if (!validarNome(nome)) {
            showWarning("Digite um nome valido!");
            return false;
        }

        if (!validarGenero(genero)) {
            showWarning("Digite um Genero de Film valido!");
            return false;
        }

        if (!validarAnoLancamento(anoLancamento)) {
            showWarning("Digite um Genero de Film valido!");
            return false;
        }

        if (!validarBilheteria(bilheteria)) {
            showWarning("Digite um Genero de Film valido!");
            return false;
        }

        if (!validarNotaIMDB(notaIMDB)) {
            showWarning("Digite um Genero de Film valido!");
            return false;
        }

        return true;

    }

    public static boolean validarNome(String nome) {
        if (nome.isEmpty()) {
            showWarning("Digite um nome real");
            return false;
        }
        return true;
    }

    public static boolean validarGenero(String genero) {
        if (genero.isEmpty()) {
            showWarning("Digite um Genero real");
            return false;
        }
        return true;
    }

    public static boolean validarAnoLancamento(String anoLancamento) {
        if (anoLancamento.isEmpty()) {
            showWarning("Digite um Ano de Lançamento real");
            return false;
        }
        return true;
    }

    public static boolean validarBilheteria(String bilheteria) {
        if (bilheteria.isEmpty()) {
            showWarning("Digite uma Bilheteria real");
            return false;
        }
        return true;
    }

    public static boolean validarNotaIMDB(String notaIMDB) {
        if (notaIMDB.isEmpty()) {
            showWarning("Digite uma Nota real do site IMDB");
            return false;
        }
        return true;
    }

}

/*Verifica se tem algum campo branco/nulo
        if (nome == null || nome.isBlank() ||
                genero == null || genero.isBlank() ||
                anoStr == null || anoStr.isBlank() ||
                bilheteriaStr == null || bilheteriaStr.isBlank() ||
                notaStr == null || notaStr.isBlank()) {

            showWarning("Preencha todos os campos obrigatórios.");
            return false;
        } */