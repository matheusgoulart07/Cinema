package com.template.controller;

import javafx.scene.input.MouseEvent;
import static com.template.validacao.FilmeValidador.*;
import static com.template.util.FormUtil.*;
import static com.template.util.DialogUtil.*;

import com.template.model.dao.CinemaDAO;
import com.template.model.dto.CinemaDTO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

public class MainController {

    @FXML private Label lblMensagem;
    @FXML private Button btnSalvar;
    @FXML private Button btnLimpar;
    @FXML private Button btnAlterar;
    @FXML private Button btnDeletar;

    @FXML private TextField txtNome;
    @FXML private TextField txtGenero;
    @FXML private TextField txtAnoLancamento;
    @FXML private TextField txtBilheteria;
    @FXML private TextField txtNotaIMDB;

    @FXML private TableView<CinemaDTO> tblCinema;
    @FXML private TableColumn<CinemaDTO, Integer> colId;
    @FXML private TableColumn<CinemaDTO, String> colNome;
    @FXML private TableColumn<CinemaDTO, String> colGenero;
    @FXML private TableColumn<CinemaDTO, Integer> colAnoLancamento;
    @FXML private TableColumn<CinemaDTO, Double> colBilheteria;
    @FXML private TableColumn<CinemaDTO, Double> colNotaIMDB;

    // Instância única para evitar criar um DAO a cada clique de botão
    private final CinemaDAO cinemaDAO = new CinemaDAO();

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colAnoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        colBilheteria.setCellValueFactory(new PropertyValueFactory<>("bilheteria"));
        colNotaIMDB.setCellValueFactory(new PropertyValueFactory<>("notaIMDB"));

        // Preenche os campos do formulário automaticamente ao clicar em um item da tabela
        tblCinema.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
            if (valorNovo != null) {
                carregarCamposFormulario(valorNovo, txtNome, txtGenero, txtAnoLancamento, txtBilheteria, txtNotaIMDB);
            }
        });

        carregarFilmes();
    }

    private void carregarFilmes() {
        tblCinema.setItems(FXCollections.observableArrayList(cinemaDAO.listarFilme()));
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (!validarFormulario(txtNome.getText(), txtGenero.getText(), txtAnoLancamento.getText(), txtBilheteria.getText(), txtNotaIMDB.getText())) {
            return;
        }

        CinemaDTO objCinemaDTO = usarDTO(txtNome, txtGenero, txtAnoLancamento, txtBilheteria, txtNotaIMDB);
        cinemaDAO.cadastrarFilme(objCinemaDTO);

        carregarFilmes();
        btnLimparAction();
        showInfo("Filme salvo com sucesso!");
    }

    @FXML
    private void btnAlterarAction(ActionEvent event) {
        CinemaDTO filmeSelecionado = tblCinema.getSelectionModel().getSelectedItem();

        if (filmeSelecionado == null) {
            showWarning("Selecione um filme na tabela para alterar.");
            return;
        }

        if (!validarFormulario(txtNome.getText(), txtGenero.getText(), txtAnoLancamento.getText(), txtBilheteria.getText(), txtNotaIMDB.getText())) {
            return;
        }

        CinemaDTO objCinemaDTO = usarDTO(txtNome, txtGenero, txtAnoLancamento, txtBilheteria, txtNotaIMDB);
        objCinemaDTO.setId(filmeSelecionado.getId());

        cinemaDAO.alterarFilme(objCinemaDTO);

        carregarFilmes();
        btnLimparAction();
        showInfo("Filme alterado com sucesso!");
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        CinemaDTO filmeSelecionado = tblCinema.getSelectionModel().getSelectedItem();

        if (filmeSelecionado == null) {
            showWarning("Selecione um filme na tabela para excluir.");
            return;
        }

        cinemaDAO.deletarFilme(filmeSelecionado.getId());

        carregarFilmes();
        btnLimparAction();
        showInfo("Filme excluído com sucesso!");
    }

    @FXML
    private void btnLimparAction() {
        limparCampos(txtNome, txtGenero, txtAnoLancamento, txtBilheteria, txtNotaIMDB);
        tblCinema.getSelectionModel().clearSelection();
    }

    @FXML
    private void carregarCampos(MouseEvent event) {
        CinemaDTO filmeSelecionado = tblCinema.getSelectionModel().getSelectedItem();
        if (filmeSelecionado != null) {
            carregarCamposFormulario(filmeSelecionado, txtNome, txtGenero, txtAnoLancamento, txtBilheteria, txtNotaIMDB);
        }
    }
}
