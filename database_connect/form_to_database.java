package com.example.accdatabase;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) {
        //Creating nodes
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        Button button = new Button("Push");
        Button button2 = new Button("Pull");
        button.setTranslateX(220);
        button.setTranslateY(75);
        button2.setTranslateX(280);
        button2.setTranslateY(75);
        //Creating labels
        Label label1 = new Label("Name: ");
        Label label2 = new Label("Email: ");
        //Setting the message with read data
        Text text = new Text("");
        //Setting font to the label
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10);

        //Displaying the message
        button2.setOnAction(e -> {
            //Retrieving data
            retrive();
        });
        /*text.setFont(font);
        text.setTranslateX(15);
        text.setTranslateY(125);
        text.setFill(Color.BROWN);
        text.maxWidth(580);
        text.setWrappingWidth(580);*/
        button.setOnAction(e -> {

            String name = textField1.getText();
            String email = textField2.getText();

            //text.setText("Hello "+name+"Welcome to Tutorialspoint. From now, we will   communicate with you at "+email);
            insert(name, email);
        });
        //Adding labels for nodes
        HBox box = new HBox(5);
        box.setPadding(new Insets(25, 5 , 5, 50));
        box.getChildren().addAll(label1, textField1, label2, textField2);
        Group root = new Group(box, button,button2, text);
        //Setting the stage
        Scene scene = new Scene(root, 595, 150, Color.BEIGE);
        stage.setTitle("Text Field Example");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {

        launch(args);

    }
    private static void showalert(String firstName, String emailId){

        int ID = 5032;


        String msg = "" + firstName + " " + emailId;
        msg += " \n";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("confirmation");

        alert.setContentText(msg);
        alert.showAndWait();
    }

    private static void retrive() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tactilevision", "root", "Kakashi");

            String query = "select * from students";

            Statement sta = connection.createStatement();
            ResultSet x = sta.executeQuery(query);

            while(x.next()){
                System.out.println(x.getString(1)+" " + x.getString(2)+" " +x.getString(3));
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    private static void insert(String name, String email){
        //System.out.println(name+" " + email);
        String sql = "INSERT INTO students(studentID,name,email) VALUES(?,?,?)";

        try (
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tactilevision", "root", "Kakashi");
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
}
