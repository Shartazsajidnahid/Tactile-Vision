package com.example.javafxloginformjdbctutorial.Controllers;

import com.example.javafxloginformjdbctutorial.Authentication.Login;
import com.example.javafxloginformjdbctutorial.HelloApplication;
import com.example.javafxloginformjdbctutorial.JdbcDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.net.URL;
import java.sql.SQLException;

public class LoginVboxControl {


    @FXML
    public VBox everything;

    @FXML
    private TextField fullNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void login(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();
        String name = fullNameField.getText();
        String password = passwordField.getText();

        if (name.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your email id");
            return;
        }
        if (password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",    "Please enter a password");
            return;
        }

        Login login = new Login();
        String feedback = login.logIN(name, password);

        if (feedback=="invalid") {
            infoBox("Please enter correct Email and Password", null, "Failed");
        }
        else {
            infoBox("Login Successful!", null, "feedback");
            navigate(feedback);
        }
    }

    public void navigate(String tablename){
        Pane view = null;
        try {
            URL fileUrl = HelloApplication.class.getResource(tablename+ "Page.fxml");
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

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


    public void toTranslate(ActionEvent actionEvent) {
        Pane view = null;
        try {
            URL fileUrl = HelloApplication.class.getResource("TranslateVbox.fxml");
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
