package com.template.util;

import com.template.model.dto.CinemaDTO;
import javafx.scene.control.TextField;

public class FormUtil {

    //Limpa os TextFields
    public static void limparCampos(TextField... campos) {
        for (TextField campo : campos) {
            if (campo != null) {
                campo.clear();
            }
        }
    }

    //Passa os dados do DTO clicado para a tela
    public static void carregarCamposFormulario(CinemaDTO dto, TextField txtNome, TextField txtGenero,
                                                TextField txtAno, TextField txtBilheteria, TextField txtNota) {
        if (dto == null) return;

        txtNome.setText(dto.getNome());
        txtGenero.setText(dto.getGenero());
        txtAno.setText(String.valueOf(dto.getAnoLancamento()));
        txtBilheteria.setText(String.valueOf(dto.getBilheteria()));
        txtNota.setText(String.valueOf(dto.getNotaIMDB()));
    }

    //Forma o objeto DTO com os dados do formulário
    public static CinemaDTO usarDTO(TextField txtNome, TextField txtGenero,
                                       TextField txtAno, TextField txtBilheteria, TextField txtNota) {
        CinemaDTO dto = new CinemaDTO();
        dto.setNome(txtNome.getText().trim());
        dto.setGenero(txtGenero.getText().trim());
        dto.setAnoLancamento(Integer.parseInt(txtAno.getText().trim()));
        dto.setBilheteria(Double.parseDouble(txtBilheteria.getText().replace(",", ".").trim()));
        dto.setNotaIMDB(Double.parseDouble(txtNota.getText().replace(",", ".").trim()));
        return dto;
    }
}
