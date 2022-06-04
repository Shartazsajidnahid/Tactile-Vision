package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class WelcomeVboxControl {

    @FXML
    private BorderPane LoginPane;
    @FXML
    private VBox everything;

    @FXML
    private AnchorPane inside;


    public void loadLogin(ActionEvent actionEvent) throws IOException {
        changePage("LoginVbox");
    }

    public void toTranslate(ActionEvent actionEvent) {
        changePage("Translate");

    }

    public void toAdmin(ActionEvent actionEvent) {
        changePage("AdminPage");

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


    public void tologin(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("Login.fxml"));
        GridPane child;
        try {
            child = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoginPane.setCenter(child);
    }
}