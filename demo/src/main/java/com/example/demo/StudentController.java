package com.example.demo;

import LoginSignup.DocumentDao;
import Management.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

public class StudentController implements Initializable {
    @FXML
    private TableView<TableViewMark> markTable;

    @FXML
    private Button addPhotos;

    @FXML
    private TextField courseID;

    @FXML
    private TableColumn<TableViewMark, String> docColumn;

    @FXML
    private TableColumn<TableViewMark, String> markColumn;

    @FXML
    private VBox everything;
    @FXML
    private Label userName;
    @FXML
    private Label email;
    @FXML
    private Label phoneNumber;

    private CurrentUser currentUser;
    private DocumentDao documentDao;
    private HashMap<Integer,String> coursemap;

    @FXML
    private ComboBox<String> courseCombo;
    private ObservableList<String> coursetypes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coursemap = new HashMap<>();
        documentDao = new DocumentDao();
        docColumn.setCellValueFactory(new PropertyValueFactory<TableViewMark,String>("docColumn"));
        markColumn.setCellValueFactory(new PropertyValueFactory<TableViewMark,String>("markColumn"));
        currentUser = CurrentUser.getInstance();
        setuserdetails();
        currentUser.toString();
        initialize_course();
    }
    private void initialize_course(){
         coursemap = documentDao.getCourses(currentUser.getId(), "Student", "Takes");

        List<String> coursenames = new ArrayList<>();
        for (Map.Entry mapElement : coursemap.entrySet()) {
            coursenames.add((String) mapElement.getValue());
        }

        coursetypes = FXCollections.observableArrayList(coursenames);
        courseCombo.setItems(coursetypes);
    }


    private void setuserdetails() {
        userName.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        phoneNumber.setText(currentUser.getPhone());
    }

    @FXML
    void LogOut(ActionEvent event) {
        currentUser.setIsset(false);
        navigate("WelcomeVbox",everything);
    }
    @FXML
    void CheckMark(ActionEvent event) {
        ObservableList<TableViewMark> list = FXCollections.observableArrayList(
                new TableViewMark("Nahid", "88"),
                new TableViewMark("aaaa", "33")
        );
    markTable.setItems(list);
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

    public void selectType(ActionEvent actionEvent) {

    }
}
