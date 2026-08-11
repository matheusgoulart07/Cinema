package com.template.controller;

import static com.template.validacao.FilmeValidador.*;
import static com.template.util.FormUtil.*;
import static com.template.util.DialogUtil.*;
import com.template.model.dao.CinemaDAO;
import com.template.model.dto.CinemaDTO;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import java.util.ArrayList;

public class MainController {

    // Conecta ao componente visual FXML
    @FXML
    private Label lblMensagem;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnDeletar;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtAnoLancamento;
    @FXML
    private TextField txtBilheteria;
    @FXML
    private TextField txtNotaIMDB;
    @FXML
    private TableView<CinemaDTO> tblCinema;
    @FXML
    private TableColumn<CinemaDTO, Integer> colId;
    @FXML
    private TableColumn<CinemaDTO, String> colNome;
    @FXML
    private TableColumn<CinemaDTO, String> colGenero;
    @FXML
    private TableColumn<CinemaDTO, Integer> colAnoLancamento;
    @FXML
    private TableColumn<CinemaDTO, Double> colBilheteria;
    @FXML
    private TableColumn<CinemaDTO, Double> colNotaIMDB;

    @FXML
    private void carregarFilmes() {
        CinemaDAO cinemaDAO = new CinemaDAO();
        ArrayList<CinemaDTO> listaFilmes = cinemaDAO.listarFilme();
        tblCinema.setItems(FXCollections.observableArrayList(listaFilmes));
    }

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colAnoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        colBilheteria.setCellValueFactory(new PropertyValueFactory<>("bilheteria"));
        colNotaIMDB.setCellValueFactory(new PropertyValueFactory<>("notaIMDB"));

        carregarFilmes();
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {

        if(validarSalvar(txtNome.getText(), txtGenero.getText(), txtAnoLancamento.getText(), txtBilheteria.getText(), txtNotaIMDB.getText())) {

        CinemaDAO cinemaDAO = new CinemaDAO();

        try {
            String nome = txtNome.getText();
            String genero = txtGenero.getText();
            int anoLancamento = Integer.parseInt(txtAnoLancamento.getText());
            double bilheteria = Double.parseDouble(txtBilheteria.getText());
            double notaImdb = Double.parseDouble(txtNotaIMDB.getText());

            CinemaDTO objCinemaDTO = new CinemaDTO();
            objCinemaDTO.setNome(nome);
            objCinemaDTO.setGenero(genero);
            objCinemaDTO.setAnoLancamento(anoLancamento);
            objCinemaDTO.setBilheteria(bilheteria);
            objCinemaDTO.setNotaIMDB(notaImdb);

            cinemaDAO.cadastrarFilme(objCinemaDTO);

            carregarFilmes();
            btnLimparAction();

            //lblMensagem.setText("Filme salvo com sucesso!");
            //lblMensagem.setStyle("-fx-text-fill: green;");
            showInfo("Filme salvo com sucesso!");

        } catch (Exception e) {
            //lblMensagem.setText("Erro ao salvar o filme.");
            //lblMensagem.setStyle("-fx-text-fill: red;");
            showError("Erro ao salvar o filme");
            }
        }
    }
    @FXML
    private void btnLimparAction() {
        limparCampo(txtNome,txtGenero,txtAnoLancamento,txtBilheteria,txtNotaIMDB);
    }

    @FXML
    private void btnAlterarAction(ActionEvent event) {

        if (validarAlterar(txtNome.getText(), txtGenero.getText(), txtAnoLancamento.getText(), txtBilheteria.getText(), txtNotaIMDB.getText())) {

            CinemaDAO cinemaDAO = new CinemaDAO();

            try {
                CinemaDTO filmeSelecionado = tblCinema.getSelectionModel().getSelectedItem();

                if (filmeSelecionado == null) {
                    //lblMensagem.setText("Selecione um filme na tabela para alterar.");
                    //lblMensagem.setStyle("-fx-text-fill: orange;");
                    showInfo("Selecione um filme na tabela para alterar.");
                    return;
                }

                CinemaDTO objCinemaDTO = new CinemaDTO();
                objCinemaDTO.setId(filmeSelecionado.getId());
                objCinemaDTO.setNome(txtNome.getText());
                objCinemaDTO.setGenero(txtGenero.getText());
                objCinemaDTO.setAnoLancamento(Integer.parseInt(txtAnoLancamento.getText()));
                objCinemaDTO.setBilheteria(Double.parseDouble(txtBilheteria.getText().replace(",", ".")));
                objCinemaDTO.setNotaIMDB(Double.parseDouble(txtNotaIMDB.getText().replace(",", ".")));

                cinemaDAO.alterarFilme(objCinemaDTO);

                carregarFilmes();
                btnLimparAction();

                //lblMensagem.setText("Filme alterado com sucesso!");
                //lblMensagem.setStyle("-fx-text-fill: green;");
                showInfo("Filme alterado com sucesso");
            } catch (Exception e) {
                //lblMensagem.setText("Erro ao alterar o filme.");
                //lblMensagem.setStyle("-fx-text-fill: red;");
                showError("Erro ao alterar o filme.");
            }
        }
        }

        @FXML
        private void btnDeletarAction(ActionEvent event) {

            CinemaDAO cinemaDAO = new CinemaDAO();

            CinemaDTO filmeSelecionado = tblCinema.getSelectionModel().getSelectedItem();

            if (filmeSelecionado == null) {
                //lblMensagem.setText("Selecione um filme na tabela para excluir.");
                //lblMensagem.setStyle("-fx-text-fill: orange;");
                showInfo("Selecione um filme na tabela para excluir.");
                return;
            }

            cinemaDAO.deletarFilme(filmeSelecionado.getId());

            carregarFilmes();
            btnLimparAction();

            //lblMensagem.setText("Filme excluído com sucesso!");
            //lblMensagem.setStyle("-fx-text-fill: red;");
            showError("Filme excluido com sucesso");
        }

    @FXML
    private void carregarCampos() {
        CinemaDTO objCinemaDTO = tblCinema.getSelectionModel().getSelectedItem();

        txtNome.setText(objCinemaDTO.getNome());
        txtGenero.setText(objCinemaDTO.getGenero());
        txtAnoLancamento.setText(String.valueOf(objCinemaDTO.getAnoLancamento()));
        txtBilheteria.setText(String.valueOf(objCinemaDTO.getBilheteria()));
        txtNotaIMDB.setText(String.valueOf(objCinemaDTO.getNotaIMDB()));

    }
        }


