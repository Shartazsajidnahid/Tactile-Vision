package com.example.demo;
import LoginSignup.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Window;


public class WelcomeVboxControl {

    @FXML
    private VBox LoginPane;
    @FXML
    private VBox everything;

    @FXML
    private AnchorPane inside;


    @FXML
    private GridPane centerPane;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField useridfield;

    @FXML
    private Button loginButton;


    @FXML
    public void login(ActionEvent event) throws SQLException {

        Window owner = loginButton.getScene().getWindow();
        String userid = useridfield.getText();
        String password = passwordfield.getText();

        if (userid.isEmpty() ) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your email id");
            return;
        }

        if (password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",    "Please enter a password");
            return;
        }
        if (userid.length()<4 ) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Invalid User ID");
            return;
        }


        Login login = new Login();
        String feedback = login.logIN(userid, password);
        System.out.println("feedback : " + feedback);
        if (feedback=="invalid") {
            infoBox("Please enter correct Email and Password", null, "Failed");
        }
        else {
            infoBox("Login Successful!", null, "feedback");
            navigate(feedback, LoginPane);
        }
    }

    public void navigate(String tablename, Pane pane){
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
        pane.getChildren().setAll(view);
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
    public void loadLogin(ActionEvent actionEvent) throws IOException {
        changePage("LoginVbox");
    }

    public void toTranslate(ActionEvent actionEvent) {
        changePage("Translate");

    }
    public void toAdmin(ActionEvent actionEvent) {
        changePage("AdminPage");

    }
    private void changePage(String pagename){
        Pane view = null;
        try {
            URL fileUrl = HelloApplication.class.getResource(pagename+".fxml");
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


    public void tologin(ActionEvent actionEvent) {
        changePage("Login");
    }
}