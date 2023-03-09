package com.alexitto.spotifyalexitto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale es = new Locale("es", "ES");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/strings", es);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main_view.fxml"), resourceBundle);
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}