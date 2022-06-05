package com.example.demo;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        docColumn.setCellValueFactory(new PropertyValueFactory<TableViewMark,String>("docColumn"));
        markColumn.setCellValueFactory(new PropertyValueFactory<TableViewMark,String>("markColumn"));

    }

    @FXML
    void LogOut(ActionEvent event) {

    }
    @FXML
    void CheckMark(ActionEvent event) {
        ObservableList<TableViewMark> list = FXCollections.observableArrayList(
                new TableViewMark("Nahid", "88"),
                new TableViewMark("aaaa", "33")
        );
    markTable.setItems(list);
    }

}
