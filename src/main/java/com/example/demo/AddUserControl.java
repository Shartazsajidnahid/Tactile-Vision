package com.example.demo;

import LoginSignup.AddUser;
import LoginSignup.DButils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddUserControl extends AnchorPane implements Initializable {
    public AddUserControl(){
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//        try {
//            fxmlLoader.load();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
    private String currentusertype;
    @FXML
    private GridPane rollAndbatchInput;

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
    private DButils dButils;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize_user_types();
        this.addUserUtil = new AddUser();
        this.dButils = new DButils();
    }
    private void initialize_user_types(){
        this.UserTypes = FXCollections.observableArrayList("Student", "Teacher", "Staff", "Admin");
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
        if(gotType=="Student"){
            setrollbatchVisible();
        }
        else{
            setrollbatchUnVisible();
        }
    }
    @FXML
    private Button addButton;
    @FXML
    private void addUser() throws SQLException, SQLException {
        System.out.println(this.currentusertype);
//        Window owner = addUserButton.getScene().getWindow();
        String name = fullNameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();


        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty() ) {

            dButils.infoBox("Fill up the fields", "" , "");
            return;

        }
        if(this.currentusertype=="Student"){
            String roll = rollField.getText();
            String batch = batchField.getText();
            if (roll.isEmpty() || batch.isEmpty()) {
                dButils.infoBox("Fill up the fields", "" , "");
                return;
            }
            this.addUserUtil.addStudent(name,password,email,phone,roll,batch);
            dButils.infoBox("User added successfully", "" , "");
            return;
        }

        this.addUserUtil.addOtherUsers(name,password,email,phone, this.currentusertype);
        dButils.infoBox("User added successfully", "" , "");

        fullNameField.clear();
        passwordField.clear();
        phoneField.clear();
        emailField.clear();
        rollField.clear();
        batchField.clear();

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
