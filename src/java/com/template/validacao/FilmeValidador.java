package com.template.validacao;

import static com.template.util.DialogUtil.*;
import javafx.beans.binding.Bindings;

public class FilmeValidador {

    public static boolean validarSalvar(String nome, String genero, String anoLancamento, String bilheteria, String notaIMDB) {

        if (nome.isEmpty() || genero.isEmpty() || anoLancamento.isEmpty() || bilheteria.isEmpty() || notaIMDB.isEmpty())
            showWarning("Preencha o(s) campo(s) faltante(s)");
            return false;
    }

    public static boolean validarAlterar(String nome, String genero, String anoLancamento, String bilheteria, String notaIMDB) {

        if (nome.isEmpty() || genero.isEmpty() || anoLancamento.isEmpty() || bilheteria.isEmpty() || notaIMDB.isEmpty())
            showWarning("Preencha o(s) campo(s) faltante(s)");
        return false;
    }

    public static boolean validarLimpar(String nome, String genero, String anoLancamento, String bilheteria, String notaIMDB) {

        if (nome.isEmpty() || genero.isEmpty() || anoLancamento.isEmpty() || bilheteria.isEmpty() || notaIMDB.isEmpty())
            showWarning("Preencha o(s) campo(s) faltante(s)");
        return false;
    }

    /* btnSalvar.disableProperty().bind(
            Bindings.createBooleanBinding(() ->
            txtNome.getText().trim().isEmpty() ||
            txtGenero.getText().trim().isEmpty() ||
            txtAnoLancamento.getText().trim().isEmpty() ||
            txtBilheteria.getText().trim().isEmpty() ||
            txtNotaIMDB.getText().trim().isEmpty(),

                        txtNome.textProperty(),
                                txtGenero.textProperty(),
                                txtAnoLancamento.textProperty(),
                                txtBilheteria.textProperty(),
                                txtNotaIMDB.textProperty()
                                )
                                );

        btnAlterar.disableProperty().bind(
            Bindings.createBooleanBinding(() ->
            txtNome.getText().trim().isEmpty() ||
            txtGenero.getText().trim().isEmpty() ||
            txtAnoLancamento.getText().trim().isEmpty() ||
            txtBilheteria.getText().trim().isEmpty() ||
            txtNotaIMDB.getText().trim().isEmpty(),

                        txtNome.textProperty(),
                                txtGenero.textProperty(),
                                txtAnoLancamento.textProperty(),
                                txtBilheteria.textProperty(),
                                txtNotaIMDB.textProperty()
                                )
                                );

    // Botão Limpar ativa quando pelo menos um campo é preenchido
        btnLimpar.disableProperty().bind(
            Bindings.createBooleanBinding(() ->
            txtNome.getText().trim().isEmpty() &&
            txtGenero.getText().trim().isEmpty() &&
            txtAnoLancamento.getText().trim().isEmpty() &&
            txtBilheteria.getText().trim().isEmpty() &&
            txtNotaIMDB.getText().trim().isEmpty(),

                        txtNome.textProperty(),
                                txtGenero.textProperty(),
                                txtAnoLancamento.textProperty(),
                                txtBilheteria.textProperty(),
                                txtNotaIMDB.textProperty()
                                )
                                ); */
}
