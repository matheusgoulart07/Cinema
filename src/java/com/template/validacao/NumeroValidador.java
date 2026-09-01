package com.template.validacao;
import static com.template.util.DialogUtil.*;

public class NumeroValidador implements Validacao<String> {

    private final String anoLancamento;

    public NumeroValidador(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public boolean validar(String valor) {

        try {
            int anoLancamento = Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            showWarning("NAO EH INTEIRO");
            return false;
        }
        return true;
    }

    @Override
    public String getMensagemErro() {
        return "Digite um numero inteiro";
    }

    @Override
    public String getValor() {
        return anoLancamento;
    }
}