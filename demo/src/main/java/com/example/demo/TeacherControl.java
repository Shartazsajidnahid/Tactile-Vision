package com.example.demo;

import LoginSignup.DocumentDao;
import Management.CurrentUser;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
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

    private ObservableList<Integer> docnames;
    private ObservableList<String> imagenames;

    private CurrentUser currentUser;
    @FXML
    private Label userName;
    @FXML
    private Label email;
    @FXML
    private Label phoneNumber;

    private DocumentDao documentDao;
    private HashMap<Integer, List<String>> doclistmap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list1 = Arrays.asList("উন্মুক্ত করা বা খোলা","অভাব আছে এমন ","কোন উপায়ে বেঁচে থাকা ");
        documentDao = new DocumentDao();
        currentUser = CurrentUser.getInstance();
        docList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        System.out.println("from Teacher");
        currentUser.toString();
        setuserdetails();

        try {
            setOutputVbox(list1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void setuserdetails() {
        userName.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        phoneNumber.setText(currentUser.getPhone());
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
    void searchCourse(ActionEvent event) throws SQLException {
        int courseid = Integer.parseInt(courseID.getText());
        HashMap<Integer, String> tempmap = documentDao.getDoclist(courseid);
         doclistmap = new HashMap<>();
        List<String> convertedList = new ArrayList<>();
        for (Map.Entry mapElement : tempmap.entrySet()) {
            String addresses = (String) mapElement.getValue();
            convertedList = Arrays.asList(addresses.split(","));
            doclistmap.put((Integer) mapElement.getKey(), convertedList);
        }
        docList.getItems().clear();
        addDoclist(doclistmap);
    }

    public void toTranslate(ActionEvent actionEvent) {
        navigate("Translate",everything);
    }
    public void navigate(String tablename, Pane pane){
        Pane view = null;
        try {
            URL fileUrl = HelloApplication.class.getResource(tablename+ ".fxml");
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

    public void logout(ActionEvent actionEvent) {
        currentUser.setIsset(false);
        navigate("WelcomeVbox",everything);
    }

    public void addDoclist(Map<Integer,List<String>> doclistMap){
        String currentFile ;
        List<Integer> selectedNames = new ArrayList<>();
        for (Map.Entry mapElement : doclistMap.entrySet()) {
            selectedNames.add((Integer) mapElement.getKey());
        }

        docnames = FXCollections.observableArrayList(selectedNames);
        docList.getItems().addAll(docnames);

        docList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                imageList.getItems().clear();
                int filename = (int) docList.getSelectionModel().getSelectedItem();
                addImageList(doclistMap.get(filename));
            }
        });
    }

    private void addImageList(List<String> strings) {


        imagenames = FXCollections.observableArrayList(strings);
        imageList.getItems().addAll(imagenames);

        imageList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                try {
                    String filename = (String) imageList.getSelectionModel().getSelectedItem();
                    List<String> allLines = new ArrayList<>();
                    try {
                        allLines = Files.readAllLines(Paths.get(filename));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setOutputVbox(allLines);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
