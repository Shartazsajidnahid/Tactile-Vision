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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TranslateVbox implements Initializable {
    @FXML
    private VBox outputVbox;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private VBox everything;
    @FXML
    private BorderPane viewBorderPane;

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

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("Imageview.fxml"));
        ScrollPane child;
        try {
            child = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewBorderPane.setCenter(child);
//        List<String> list1 = Arrays.asList("উন্মুক্ত করা বা খোলা","অভাব আছে এমন ","কোন উপায়ে বেঁচে থাকা ");
        ViewImage viewImage = fxmlLoader.getController();
        child.setFitToWidth(true);
        viewImage.setShowImage(image);

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
        List<String> outputTextList = new ArrayList<>();
        for(String x : selectedFromInputlist){
            Image image = new Image(new FileInputStream(x));
            outputTextList = app.main(x);
        }
//        changeCenterPane(viewBorderPane, "outputShow");
        setOutputVbox(outputTextList);
//        addLabels(outputTextList);
        selectedFromInputlist = new ArrayList<>();
    }

    public void setOutputVbox( List<String> list1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("outputShow.fxml"));
        ScrollPane child;
        try {
            child = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewBorderPane.setCenter(child);
//        List<String> list1 = Arrays.asList("উন্মুক্ত করা বা খোলা","অভাব আছে এমন ","কোন উপায়ে বেঁচে থাকা ");
        Outputshow outputshowController = fxmlLoader.getController();
        child.setFitToWidth(true);
        outputshowController.addLabels(list1);
    }



    public void addLabels(List<String> list1){
        for(String x : list1){
            Label label = new Label();
            label.setText(x);
            // outputVbox.setAlignment(Pos.CENTER);
            outputVbox.getChildren().add(label);
        }
    }

    private void changeCenterPane(BorderPane pane, String paneName){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource(paneName+".fxml"));
        ScrollPane child;
        try {
            child = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pane.setCenter(child);
    }
}