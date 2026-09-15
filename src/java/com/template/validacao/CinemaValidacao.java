package com.template.validacao;
import java.util.ArrayList;
import java.util.List;
import static com.template.util.DialogUtil.*;

public class CinemaValidacao implements IFilmeValidacao {

    public boolean validarFilme(String nome, String genero, String anoLancamento, String bilheteria, String notaIMDB) {

        List<Validacao<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidacao("Nome", nome));
        validadores.add(new CampoObrigatorioValidacao("Genero", genero));
        validadores.add(new CampoObrigatorioValidacao("Ano de Lancamento", anoLancamento));
        validadores.add(new CampoObrigatorioValidacao("Bilheteria", bilheteria));
        validadores.add(new CampoObrigatorioValidacao("Nota no IMDB", notaIMDB));

        validadores.add(new GeneroValidacao(genero));
        validadores.add(new NumeroValidacao(anoLancamento));

        for (Validacao<String> validador : validadores) {

            if (!validador.validar(validador.getValor())) {
                showWarning(validador.getMensagemErro());
                return false;
            }

        }
        return true;
    }

}
