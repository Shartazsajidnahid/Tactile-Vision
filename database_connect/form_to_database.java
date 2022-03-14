package com.example.accdatabase;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloApplication extends Application {


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration");
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(pane, 300, 275);

        Text sceneTitle = new Text("Tax Calculator");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
        pane.add(sceneTitle, 0, 0, 2, 1);
        Label total = new Label("Name:");
        pane.add(total, 0, 1);
        final TextField totalField = new TextField();
        pane.add(totalField, 1, 1);
        Label percent = new Label("Email:");
        pane.add(percent,0,2);
        final TextField percentField = new TextField();
        pane.add(percentField, 1, 2);

        Button calculateButton = new Button("Create Account");
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(calculateButton);
        pane.add(hbox, 1, 4);

        final Text taxMessage = new Text();
        pane.add(taxMessage, 1, 6);

        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }

            public void actionPerformed(ActionEvent e) {
                String firstName = totalField.getText();
                String emailId = percentField.getText();
                int ID = 5032;


                String msg = "" + firstName;
                msg += " \n";
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("confirmation");

                alert.setContentText(msg);
                alert.showAndWait();

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tactilevision", "root", "Kakashi");

                    String query = "INSERT INTO students (studentID, name, email)  VALUES (ID,firstName,emailId)";

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);



                    if (x == 0) {
                        alert.setContentText("This is alredy exist");
                    } else {

                        alert.setContentText("Your account is sucessfully created");
                    }
                    alert.showAndWait();
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });




        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        retrive();
       // launch(args);
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
}
