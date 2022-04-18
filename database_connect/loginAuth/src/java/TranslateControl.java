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

    private ObservableList<String> selectednamelist;

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

                names.add(file.getAbsolutePath());
            }
            selectednamelist = FXCollections.observableArrayList(names);

            addDynamicButton(selectednamelist);
         }
    }

    public void showimage(String file) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(file));
        showImage.setImage(image);
    }
}

