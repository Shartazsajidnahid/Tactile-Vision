package com.example.javafxloginformjdbctutorial.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserControl {

    @FXML
    private AnchorPane rollAndbatchInput;

    @FXML
    private ComboBox typeSelector;


    @FXML
    public void initialize() {
        initialize_user_types();
    }

    private ObservableList<String> UserTypes;


//    public void initialize(URL url, ResourceBundle resourceBundle){
//
//    }
    private void initialize_user_types(){
        this.UserTypes = FXCollections.observableArrayList("student", "teacher", "staff", "admin");
        typeSelector.setItems(UserTypes);
    }

    public void setrollbatchVisible(){
        rollAndbatchInput.setVisible(true);
    }

    public void setrollbatchUnVisible(){
        rollAndbatchInput.setVisible(false);
    }


    public void selectType(ActionEvent actionEvent) {
        String gotType = typeSelector.getSelectionModel().getSelectedItem().toString();
        if(gotType=="student"){
            setrollbatchVisible();
        }
        else{
            setrollbatchUnVisible();
        }
    }
}
