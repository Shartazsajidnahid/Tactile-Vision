package com.example.javafxloginformjdbctutorial.Controllers;

import com.example.javafxloginformjdbctutorial.HelloApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class TranslateControl {
    @FXML
    public AnchorPane everything;
    @FXML
    public GridPane gridpane;
    @FXML
    public VBox vbox;

    @FXML
    public ListView inputImageList;

    @FXML
    public ImageView showImage;

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

    public void addDynamicButton(ObservableList<String> names){

         String currentFile ;
        inputImageList.getItems().addAll(names);

        inputImageList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                try {
                    showimage((String) inputImageList.getSelectionModel().getSelectedItem());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //System.out.println( );
            }
        });

    }
    private void showImage(String name) throws MalformedURLException {
        Stage primaryStage = (Stage)(everything.getScene().getWindow());
        final Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        stage.setScene(dialogScene);
        ImageView imageView = new ImageView();
        File file = new File(name);
        String imagepath = file.toURI().toURL().toString();
        System.out.println("file:"+imagepath);
        Image image = new Image(imagepath);
        imageView.setImage(image);
        Group root = new Group(imageView);
        Scene scene = new Scene(root);
        stage.setTitle("Displaying Image");
        stage.setScene(scene);
        stage.show();
    }

    public void addPhotos() throws MalformedURLException {

        Stage primaryStage = (Stage)(everything.getScene().getWindow());
        final Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);


        FileChooser fileChooser = new FileChooser();
       // Stage stage = (Stage)(everything.getScene().getWindow());
        List<File> fileList =  fileChooser.showOpenMultipleDialog(stage);
        ArrayList<Button> buttons = new ArrayList<>();

        if (fileList != null) {
            ArrayList<String> names = new ArrayList<>();
            for(File file: fileList){
                ImageView imageView = new ImageView();
                String imagepath = file.toURI().toURL().toString();
               // System.out.println(file.getName());
                //addDynamicButton(file.getName());
//                buttons.add(new Button(file.getAbsolutePath()));
                names.add(file.getAbsolutePath());
            }
            ObservableList<String> namelist = FXCollections.observableArrayList(names);

            addDynamicButton(namelist);
         }
    }

    public void showimage(String file) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(file));
        showImage.setImage(image);
    }
}

