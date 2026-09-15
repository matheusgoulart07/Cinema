package com.template.main;

import com.template.controller.MainController;
import com.template.validacao.CinemaValidacao;
import com.template.validacao.IFilmeValidacao;
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

        IFilmeValidacao movieValidador = new CinemaValidacao();

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        loader.setControllerFactory(controllerClass -> {
            if(controllerClass == MainController.class){
                return new MainController(movieValidador);
            }
            try{
                return controllerClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        Scene scene = new Scene(loader.load(), 600, 450);

        stage.setTitle("Cadastro de Filmes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}





