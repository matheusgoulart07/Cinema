package com.template.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Busca o arquivo direto na raiz de resources
        URL fxmlLocation = getClass().getResource("/main.fxml");

        if (fxmlLocation == null) {
            throw new IllegalStateException("Não foi possível encontrar o arquivo /main.fxml na pasta resources.");
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(loader.load(), 600, 450);

        stage.setTitle("Cadastro de Filmes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}