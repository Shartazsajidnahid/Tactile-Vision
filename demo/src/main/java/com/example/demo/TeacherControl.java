package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TeacherControl implements Initializable {
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private BorderPane viewBorderPane;


    @FXML
    private Label aboutUse;

    @FXML
    private Button addMark;

    @FXML
    private TextField courseID;

    @FXML
    private ListView docList;

    @FXML
    private VBox everything;

    @FXML
    private ListView imageList;

    @FXML
    private Button logoutbutton;

    @FXML
    private TextField mark;

    @FXML
    private Button seachCourse;

    @FXML
    private Button toTranslate;

    @FXML
    private VBox userInfoVbox;

    private ObservableList<String> docnames;
    private ObservableList<String> imagenames;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list1 = Arrays.asList("উন্মুক্ত করা বা খোলা","অভাব আছে এমন ","কোন উপায়ে বেঁচে থাকা ");

        try {
            setOutputVbox(list1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setOutputVbox(List<String> list1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("outputShow.fxml"));
        ScrollPane child;
        try {
            child = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewBorderPane.setCenter(child);
        child.setFitToWidth(true);
//        child.setContent(new Label("nahid"));
        Outputshow outputshowController = fxmlLoader.getController();
        outputshowController.addLabels(list1);
    }

    @FXML
    void addMark(ActionEvent event) {

    }

    @FXML
    void searchCourse(ActionEvent event) {

    }

//    public void addDoclist(Map<String,ObservableList<String>> doclistMap){
//        String currentFile ;
//        List<String> selectedNames = new ArrayList<>();
//        for (Map.Entry mapElement : doclistMap.entrySet()) {
//            selectedNames.add((String)mapElement.getKey());
//        }
//
//        docnames = FXCollections.observableArrayList(selectedNames);
//        docList.getItems().addAll(docnames);
//
//        docList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observableValue, Object o, Object t1) {
//                try {
//                    String filename = (String) docList.getSelectionModel().getSelectedItem();
//                    addImageList(doclistMap.get(filename));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }
//
//    private void addImageList(ObservableList<String> strings) {
//
//
//        imagenames = FXCollections.observableArrayList(strings);
//        imageList.getItems().addAll(imagenames);
//
//        docList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observableValue, Object o, Object t1) {
//                try {
//                    String filename = (String) imageList.getSelectionModel().getSelectedItem();
//                    setOutputVbox();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }
}
