package com.example.javafxloginformjdbctutorial.Controllers;

import com.example.javafxloginformjdbctutorial.Authentication.AddUser;
import com.example.javafxloginformjdbctutorial.Authentication.Login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddUserControl {
    private String currentusertype;
    @FXML
    private AnchorPane rollAndbatchInput;

    @FXML
    private ComboBox typeSelector;

    @FXML
    private TextField fullNameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField rollField;
    @FXML
    private TextField batchField;

    private ObservableList<String> UserTypes;
    private AddUser addUserUtil;

    @FXML
    public void initialize() {
        initialize_user_types();
        this.addUserUtil = new AddUser();
    }



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
        this.currentusertype = gotType;
        if(gotType=="student"){
            setrollbatchVisible();
        }
        else{
            setrollbatchUnVisible();
        }
    }

    @FXML
    private void addUser() throws SQLException {
        System.out.println(this.currentusertype);
//        Window owner = addUserButton.getScene().getWindow();
        String name = fullNameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty() ) {
            System.out.println( "Please enter your email id");
            return;
        }


        if(this.currentusertype=="student"){
            String roll = rollField.getText();
            String batch = batchField.getText();
            if (roll.isEmpty() || batch.isEmpty()) {
                System.out.println( "Please enter your email id");
                return;
            }
            this.addUserUtil.addStudent(name,password,email,phone,roll,batch);
            return;
        }

        this.addUserUtil.addOtherUsers(name,password,email,phone, this.currentusertype);


//        if (feedback=="invalid") {
//            System.out.println("Please enter correct Email and Password");
//        }
//        else {
//            System.out.println("Register Successful!");
////            infoBox("Login Successful!", null, "feedback");
////            navigate(feedback);
//        }
    }
}
