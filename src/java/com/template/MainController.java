package com.template;

import com.sun.source.doctree.TextTree;
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
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnListar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
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
        CinemaDAO objCinemaDAO = new CinemaDAO();
        ArrayList<CinemaDTO> listaFilmes = objCinemaDAO.listarFilme();
        tblCinema.setItems(FXCollections.observableArrayList(listaFilmes));
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
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

        CinemaDAO objCinemaDAO = new CinemaDAO();
        objCinemaDAO.cadastrarFilme(objCinemaDTO);

        carregarFilmes();
    }

    @FXML
    private void btnListarAction() {
        carregarFilmes();
    }

    @FXML
    private void btnAlterarAction(ActionEvent event) {
        CinemaDTO filmeSelecionado = tblCinema.getSelectionModel().getSelectedItem();

        if (filmeSelecionado != null) {
            CinemaDTO objCinemaDTO = new CinemaDTO();
            objCinemaDTO.setId(filmeSelecionado.getId());
            objCinemaDTO.setNome(txtNome.getText());
            objCinemaDTO.setGenero(txtGenero.getText());
            objCinemaDTO.setAnoLancamento(Integer.parseInt(txtAnoLancamento.getText()));
            objCinemaDTO.setBilheteria(Double.parseDouble(txtBilheteria.getText().replace(",", ".")));
            objCinemaDTO.setNotaIMDB(Double.parseDouble(txtNotaIMDB.getText().replace(",", ".")));

            CinemaDAO objCinemaDAO = new CinemaDAO();
            objCinemaDAO.alterarFilme(objCinemaDTO);

            carregarFilmes();
        }
    }

        @FXML
        private void btnDeletarAction (ActionEvent event){
            CinemaDTO filmeSelecionado = tblCinema.getSelectionModel().getSelectedItem();

            if (filmeSelecionado != null) {
                CinemaDAO objCinemaDAO = new CinemaDAO();

                objCinemaDAO.deletarFilme(filmeSelecionado.getId());

                carregarFilmes();
            }
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
    private void carregarCampos() {
        CinemaDTO objCinemaDTO = tblCinema.getSelectionModel().getSelectedItem();

        if (objCinemaDTO != null) {
            txtNome.setText(objCinemaDTO.getNome());
            txtGenero.setText(objCinemaDTO.getGenero());
            txtAnoLancamento.setText(String.valueOf( objCinemaDTO.getAnoLancamento()));
            txtBilheteria.setText(String.valueOf( objCinemaDTO.getBilheteria()));
            txtNotaIMDB.setText(String.valueOf( objCinemaDTO.getNotaIMDB()));
        }
    }

    }

