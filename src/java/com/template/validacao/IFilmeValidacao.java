package com.template.validacao;

public interface IFilmeValidacao {
    boolean validarFilme(String nome, String genero, String anoLancamento, String bilheteria, String notaIMDB);
}
