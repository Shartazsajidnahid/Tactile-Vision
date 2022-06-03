package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.imageio.IIOException;
import java.io.IOException;

public class AddUser extends AnchorPane {
    public AddUser(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
