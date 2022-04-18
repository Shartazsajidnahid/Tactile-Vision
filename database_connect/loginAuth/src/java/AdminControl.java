package com.example.javafxloginformjdbctutorial.Controllers;

import com.example.javafxloginformjdbctutorial.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class AdminControl {
    @FXML
    public VBox everything;

    @FXML
    public AnchorPane inside;


    public void Logout(ActionEvent actionEvent) throws IOException {
        changePage("WelcomeVbox");
    }

    public void toTranslate(ActionEvent actionEvent) {
        changePage("TranslateVbox");
    }

    private void changePage(String pagename){
        Pane view = null;
        try {
            URL fileUrl = HelloApplication.class.getResource(pagename+".fxml");
            if(fileUrl == null){
                throw new java.io.FileNotFoundException("fxml file not found");
            }
            view = new FXMLLoader().load(fileUrl);
        }catch (Exception e){
            System.out.println("file not found");
        }
        FXMLLoader login = new FXMLLoader();
        everything.getChildren().setAll(view);
    }

}
