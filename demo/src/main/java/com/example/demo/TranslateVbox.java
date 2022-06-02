package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

public class TranslateVbox {

    @FXML
    public VBox everything;

    @FXML
    public ListView inputImageList;
    @FXML
    public ListView outputImageList;

    @FXML
    public ImageView showImage;

    ObservableList<String> namelist;

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

    public void addPhotos() throws MalformedURLException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        // Stage stage = (Stage)(everything.getScene().getWindow());
        List<File> fileList =  fileChooser.showOpenMultipleDialog(stage);

        ArrayList<String> Selectednames = new ArrayList<>();

        if (fileList != null) {
            for(File file: fileList){
                //ImageView imageView = new ImageView();
                String imagepath = file.toURI().toURL().toString();
                Selectednames.add(file.getAbsolutePath());
            }
            namelist = FXCollections.observableArrayList(Selectednames);
            addDynamicButton(namelist);
        }
    }

    public void showimage(String file) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(file));
        showImage.setImage(image);
    }

    public void toWelcome(ActionEvent actionEvent){
        Pane view = null;
        try {
            URL fileUrl = HelloApplication.class.getResource("WelcomeVbox.fxml");
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