package com.template.validacao;
import static com.template.util.DialogUtil.*;

public class NumeroValidacao implements Validacao<String> {

    private final String anoLancamento;

    public NumeroValidacao(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public boolean validar(String valor) {

        try {
            int anoLancamento = Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public String getMensagemErro() {
        return "Digite um numero inteiro que corresponda a algum ano!!!";
    }

    @Override
    public String getValor() {
        return anoLancamento;
    }
}