package com.example.demo;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.List;

public class Outputshow {

    @FXML
    private VBox outputVbox;
    @FXML
    private ScrollPane scrollpane;

    public void addLabels(List<String> list1){
        for(String x : list1){
            Label label = new Label();
            label.setText(x);
            label.setFont(new Font(20));
           // outputVbox.setAlignment(Pos.CENTER);
            outputVbox.getChildren().add(label);
        }
    }
}
