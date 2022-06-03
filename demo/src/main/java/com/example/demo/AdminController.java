package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private VBox everything;
    @FXML
    private GridPane centerPane;

    @FXML
    private BorderPane mainBorderPane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        AddUser addUser = new AddUser();
//        mainBorderPane.setCenter(addUser);
//        changePage("AddUser",centerPane);
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("Translate.fxml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("AddUser.fxml"));
        GridPane gg;
        try {
             gg = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainBorderPane.setCenter(gg);
    }

@FXML
    public void LogOut(ActionEvent actionEvent) throws IOException {
        changePage("WelcomeVbox", everything);
    }
    private void changePage(String pagename, Pane pane){
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
        pane.getChildren().setAll(view);
    }

}
