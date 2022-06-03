package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("WelcomeVbox.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println("Height: " + screenBounds.getHeight() + " Width: " + screenBounds.getWidth());

        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
//        TextToImage ti = new TextToImage();
//        ti.testTOimage();
    }
}