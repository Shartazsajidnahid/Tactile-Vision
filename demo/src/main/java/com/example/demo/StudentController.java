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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
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

        setactionsCombo();
    }

    private void setactionsCombo(){
        courseCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                    String filename = (String) courseCombo.getSelectionModel().getSelectedItem();
                    showMarks(getKeysByValue(filename));
            }
        });
    }

    private void showMarks(int cid) {
        HashMap<Integer, Integer> coursemarksmap = documentDao.getMarks(cid);
        Integer totoal = 0;
//        for (Map.Entry mapElement : coursemarksmap.entrySet()) {
//            System.out.println("id = " + mapElement.getKey() + " mark = " + mapElement.getValue());
//
//        }
        addtoTable(coursemarksmap);


    }

    private void addtoTable(HashMap<Integer, Integer> markmap) {
        List<TableViewMark> marklisttable = new ArrayList<>();
        for(Map.Entry mapElement : markmap.entrySet()){
            marklisttable.add(new TableViewMark((Integer) mapElement.getKey(), (Integer) mapElement.getValue()));
        }
        ObservableList<TableViewMark> list = FXCollections.observableArrayList(marklisttable);
        markTable.setItems(list);
    }

    private int getKeysByValue(String x ) {
        int key =-1;

        for (Map.Entry mapElement : coursemap.entrySet()) {
            if(x.equals(mapElement.getValue())){
                return (int) mapElement.getKey();
            }
        }
        return key;
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
