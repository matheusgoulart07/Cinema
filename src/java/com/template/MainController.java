package com.template;

import com.sun.source.doctree.TextTree;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnListar;
    @FXML private Button btnAlterar;
    @FXML private Button btnExcluir;
    @FXML private TextField txtNome;
    @FXML private TextField txtGenero;
    @FXML private TextField txtAnoLancamento;
    @FXML private TextField txtBilheteria;
    @FXML private TextField txtNotaImdb;
    @FXML private TableView<CinemaDTO> tblCinema;
    @FXML private TableColumn<CinemaDTO,String> colId;
    @FXML private TableColumn<CinemaDTO,String> colNome;
    @FXML private TableColumn<CinemaDTO,String> colGenero;
    @FXML private TableColumn<CinemaDTO,String> colAnoLancamento;
    @FXML private TableColumn<CinemaDTO,String> colBilheteria;
    @FXML private TableColumn<CinemaDTO,String> colNotaImdb;

    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}