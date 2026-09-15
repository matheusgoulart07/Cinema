package com.template.validacao;

import static com.template.util.DialogUtil.*;

public class FilmeValidacao implements IFilmeValidacao {

    @Override
    public boolean validarFilme(String nome, String genero, String anoLancamento, String bilheteria, String notaIMDB) {

        // 1. Validação de campos em branco
        if (isNullOuBlank(nome) || isNullOuBlank(genero) || isNullOuBlank(anoLancamento)
                || isNullOuBlank(bilheteria) || isNullOuBlank(notaIMDB)) {
            showWarning("Preencha todos os campos obrigatórios.");
            return false;
        }

        // 2. Validação do Nome
        if (nome.trim().length() < 2) {
            showWarning("Digite um nome válido para o filme.");
            return false;
        }

        // 3. Validação do Gênero
        GeneroValidacao generoValidacao = new GeneroValidacao(genero);
        if (!generoValidacao.validar(genero)) {
            return false;
        }

        // 4. Validação do Ano de Lançamento
        NumeroValidacao anoValidacao = new NumeroValidacao(anoLancamento);
        if (!anoValidacao.validar(anoLancamento)) {
            return false;
        }

        // 5. Validação da Bilheteria
        if (!validarDecimal(bilheteria, "Bilheteria inválida. Digite um valor numérico.")) {
            return false;
        }

        // 6. Validação da Nota IMDB
        if (!validarDecimal(notaIMDB, "Nota IMDB inválida. Digite um valor numérico.")) {
            return false;
        }

        return true;
    }

    private boolean isNullOuBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    private boolean validarDecimal(String valor, String mensagemErro) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            showWarning(mensagemErro);
            return false;
        }
    }
}