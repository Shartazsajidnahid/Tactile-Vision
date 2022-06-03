package com.example.demo;

import DotIdentifier.App;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TranslateVbox implements Initializable {

    @FXML
    private VBox everything;

    @FXML
    private ListView inputImageList;
    @FXML
    private ListView outputImageList;

    @FXML
    private ImageView showImage;

    private ObservableList<String> namelist;

    private List<String> selectedFromInputlist;
    private App app;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("whatsup");
        selectedFromInputlist = new ArrayList<>();
        app = new App();
        inputImageList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addDynamicButton(ObservableList<String> names){

        String currentFile ;
        inputImageList.getItems().addAll(names);
        inputImageList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                try {
                    String filename = (String) inputImageList.getSelectionModel().getSelectedItem();
                    showimage(filename);
                    selectedFromInputlist.add(filename);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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
    public void showimage2(File file) throws IOException {
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

    public void Convert(ActionEvent actionEvent) throws IOException {
        for(String x : selectedFromInputlist){
            Image image = new Image(new FileInputStream(x));
            app.main(x);
        }
        selectedFromInputlist = new ArrayList<>();
    }

    public void save(ActionEvent actionEvent) throws IOException {
        for(String x : selectedFromInputlist){
            app.main(x);
        }
    }
}