package com.template;

import com.sun.source.doctree.TextTree;
import javafx.beans.binding.Bindings;
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
    private Button btnSalvar;
    @FXML
    private Button btnLimpar;
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

    // Instância única global do DAO para a classe inteira usar
    private final CinemaDAO cinemaDAO = new CinemaDAO();

    @FXML
    private void carregarFilmes() {
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

        // Botão Salvar e Alterar ficam desativados enquanto todos os campos não estiverem preenchidos.
        btnSalvar.disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                                txtNome.getText().trim().isEmpty() ||
                                        txtGenero.getText().trim().isEmpty() ||
                                        txtAnoLancamento.getText().trim().isEmpty() ||
                                        txtBilheteria.getText().trim().isEmpty() ||
                                        txtNotaIMDB.getText().trim().isEmpty(),

                        txtNome.textProperty(),
                        txtGenero.textProperty(),
                        txtAnoLancamento.textProperty(),
                        txtBilheteria.textProperty(),
                        txtNotaIMDB.textProperty()
                )
        );

        btnAlterar.disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                                txtNome.getText().trim().isEmpty() ||
                                        txtGenero.getText().trim().isEmpty() ||
                                        txtAnoLancamento.getText().trim().isEmpty() ||
                                        txtBilheteria.getText().trim().isEmpty() ||
                                        txtNotaIMDB.getText().trim().isEmpty(),

                        txtNome.textProperty(),
                        txtGenero.textProperty(),
                        txtAnoLancamento.textProperty(),
                        txtBilheteria.textProperty(),
                        txtNotaIMDB.textProperty()
                )
        );

        // Botão Limpar ativa quando pelo menos um campo é preenchido
        btnLimpar.disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                                txtNome.getText().trim().isEmpty() &&
                                        txtGenero.getText().trim().isEmpty() &&
                                        txtAnoLancamento.getText().trim().isEmpty() &&
                                        txtBilheteria.getText().trim().isEmpty() &&
                                        txtNotaIMDB.getText().trim().isEmpty(),

                        txtNome.textProperty(),
                        txtGenero.textProperty(),
                        txtAnoLancamento.textProperty(),
                        txtBilheteria.textProperty(),
                        txtNotaIMDB.textProperty()
                )
        );
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        String nome = txtNome.getText();
        String genero = txtGenero.getText();
        int anoLancamento = Integer.parseInt(txtAnoLancamento.getText());
        double bilheteria = Double.parseDouble(txtBilheteria.getText());
        double notaImdb = Double.parseDouble(txtNotaIMDB.getText());

        CinemaDTO objCinemaDTO = new CinemaDTO();
        // enviando os dados para o objeto
        objCinemaDTO.setNome(nome);
        objCinemaDTO.setGenero(genero);
        objCinemaDTO.setAnoLancamento(anoLancamento);
        objCinemaDTO.setBilheteria(bilheteria);
        objCinemaDTO.setNotaIMDB(notaImdb);

        cinemaDAO.cadastrarFilme(objCinemaDTO);

        carregarFilmes();
        btnLimparAction();
    }

    @FXML
    private void btnLimparAction() {
        txtNome.clear();
        txtGenero.clear();
        txtAnoLancamento.clear();
        txtBilheteria.clear();
        txtNotaIMDB.clear();
    }

    @FXML
    private void btnAlterarAction(ActionEvent event) {
        // Essa linha vai até a tabela e descobre qual linha o usuário clicou com o mouse.
        CinemaDTO filmeSelecionado = tblCinema.getSelectionModel().getSelectedItem();

        CinemaDTO objCinemaDTO = new CinemaDTO();
        // pega o id do filme que sera alterado
        objCinemaDTO.setId(filmeSelecionado.getId());
        objCinemaDTO.setNome(txtNome.getText());
        objCinemaDTO.setGenero(txtGenero.getText());
        objCinemaDTO.setAnoLancamento(Integer.parseInt(txtAnoLancamento.getText()));
        objCinemaDTO.setBilheteria(Double.parseDouble(txtBilheteria.getText().replace(",", ".")));
        objCinemaDTO.setNotaIMDB(Double.parseDouble(txtNotaIMDB.getText().replace(",", ".")));

        cinemaDAO.alterarFilme(objCinemaDTO);

        carregarFilmes();
        btnLimparAction();
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        CinemaDTO filmeSelecionado = tblCinema.getSelectionModel().getSelectedItem();

        cinemaDAO.deletarFilme(filmeSelecionado.getId());

        carregarFilmes();
        btnLimparAction();
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