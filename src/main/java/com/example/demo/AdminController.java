package com.example.demo;

import Management.CurrentUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private Button addUser;

    @FXML
    private Label email;

    @FXML
    private Button logoutbutton;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label userName;
    @FXML
    private VBox everything;
    @FXML
    private GridPane centerPane;
    @FXML
    private BorderPane mainBorderPane;
    private CurrentUser currentUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeCenterPane(mainBorderPane, "AddUser");
        currentUser = CurrentUser.getInstance();
        System.out.println("from admin");
        setuserdetails();
    }

    private void setuserdetails() {
        userName.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        phoneNumber.setText(currentUser.getPhone());
    }

    private void changeCenterPane(BorderPane pane, String paneName){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource(paneName+".fxml"));
        GridPane child;
        try {
            child = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pane.setCenter(child);
    }

@FXML
    public void LogOut(ActionEvent actionEvent) throws IOException {
        currentUser.setIsset(false);
     //   navigate("WelcomeVbox",everything);
        changePage("WelcomeVbox", everything);
    }
    private void changePage(String pagename, Pane pane){
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
        pane.getChildren().setAll(view);
    }

    public void loadAddUser(ActionEvent actionEvent) {
        changeCenterPane(mainBorderPane, "AddUser");
    }

    public void loadAddCourse(ActionEvent actionEvent) {
        changeCenterPane(mainBorderPane, "AddCourse");
    }

    public void loadRemoveUser(ActionEvent actionEvent) {
        changeCenterPane(mainBorderPane, "RemoveUser");
    }

    public void loadRemoveCourse(ActionEvent actionEvent) {
        changeCenterPane(mainBorderPane, "RemoveCourse");
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

    public void loadAddStudenttoCourse(ActionEvent actionEvent) {
        changeCenterPane(mainBorderPane, "AddStudentToCourse");
    }

    public void loadAddTeachertoCourse(ActionEvent actionEvent) {
        changeCenterPane(mainBorderPane, "AddTeacherToCourse");
    }
}
