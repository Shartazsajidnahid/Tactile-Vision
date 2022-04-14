package com.example.javafxloginformjdbctutorial.Controllers;

import com.example.javafxloginformjdbctutorial.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;



public class TranslateControl {
    @FXML
    public AnchorPane everything;
    @FXML
    public GridPane gridpane;
    @FXML
    public VBox vbox;


    public void toWelcome(ActionEvent actionEvent) {
        Pane view = null;
        try {
            URL fileUrl = HelloApplication.class.getResource("WelcomePage.fxml");
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
    public void addPhotos() throws MalformedURLException {
        Stage primaryStage = (Stage)(everything.getScene().getWindow());
        final Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        stage.setScene(dialogScene);


        FileChooser fileChooser = new FileChooser();
       // Stage stage = (Stage)(everything.getScene().getWindow());
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            ImageView imageView = new ImageView();
            String imagepath = file.toURI().toURL().toString();
            System.out.println("file:"+imagepath);
            Image image = new Image(imagepath);
            imageView.setImage(image);
            Group root = new Group(imageView);
            Scene scene = new Scene(root, 595, 370);
            stage.setTitle("Displaying Image");
            stage.setScene(scene);
            stage.show();
         }
    }
    public void addDynamicButton(){

        vbox.setSpacing(10);
        for( int i=0; i < 10; i++) {
            Button button = new Button("Buttons" + i);
            vbox.getChildren().add( button);
        }

    }
}