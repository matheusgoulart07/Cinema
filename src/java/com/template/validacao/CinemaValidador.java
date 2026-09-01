package com.template.validacao;
import java.util.ArrayList;
import java.util.List;
import static com.template.util.DialogUtil.*;

public class CinemaValidador {

    public boolean validarFilme(String nome, String genero, String anoLancamento, String bilheteria, String notaIMDB) {

        List<Validacao<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidador("Nome", nome));
        validadores.add(new CampoObrigatorioValidador("Genero", genero));
        validadores.add(new CampoObrigatorioValidador("Ano de Lancamento", anoLancamento));
        validadores.add(new CampoObrigatorioValidador("Bilheteria", bilheteria));
        validadores.add(new CampoObrigatorioValidador("Nota no IMDB", notaIMDB));

        validadores.add(new NumeroValidador(anoLancamento));

        for (Validacao<String> validador : validadores) {

            if (!validador.validar(validador.getValor())) {
                showWarning(validador.getMensagemErro());
                return false;
            }

        }
        return true;
    }

}
