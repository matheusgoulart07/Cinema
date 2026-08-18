package com.template.validacao;

public interface Validacao<T> {
    boolean validar(T valor);
    String getMensagemErro();
    T getValor();
}

