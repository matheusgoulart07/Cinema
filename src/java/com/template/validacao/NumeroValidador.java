package com.template.validacao;

public class NumeroValidador implements Validacao<String> {

    private final String nomeCampo;
    private final String valor;
    private final boolean apenasInteiro; // Define se aceita ponto/vírgula ou só inteiro

    public NumeroValidador(String nomeCampo, String valor, boolean apenasInteiro) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
        this.apenasInteiro = apenasInteiro;
    }

    @Override
    public boolean validar(String valor) {
        if (this.valor == null || this.valor.trim().isEmpty()) return false;

        try {
            if (apenasInteiro) {
                Integer.parseInt(this.valor.trim());
            } else {
                Double.parseDouble(this.valor.replace(",", ".").trim());
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
        String tipo = apenasInteiro ? "inteiro" : "decimal";
        return "O campo " + nomeCampo + " deve conter um número " + tipo + " válido.";
    }

    @Override
    public String getValor() {
        return valor;
    }
}