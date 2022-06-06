package com.example.demo;

import DotIdentifier.App;
import LoginSignup.DocumentDao;
import Management.CurrentUser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class StaffController implements Initializable {

    private CurrentUser currentUser;
    @FXML
    private Label userName;
    @FXML
    private Label email;
    @FXML
    private Label phoneNumber;
    @FXML
    private VBox everything;

    private ObservableList<String> outputnamelist;
    private List<String> selectedFromOutputlist;

    @FXML
    private ListView outputImageList;
    @FXML
    private BorderPane viewBorderPane;
    private HashMap<String, List<String>> ListviewOutput;
    private FileChooser fileChooser;
    private App app;
    @FXML
    private TextField courseID;
    @FXML
    private TextField studentID;
    private DocumentDao documentDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        documentDao = new DocumentDao();
        app = new App();
        currentUser = CurrentUser.getInstance();
//        outputImageList = new ListView();
        ListviewOutput = new HashMap<>();
        selectedFromOutputlist = new ArrayList<>();
        fileChooser = new FileChooser();
        outputImageList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setuserdetails();

    }

    public void addPhotos() throws IOException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        HashMap<String,List<String>> tempMap = new HashMap<>();
        List<String> outputTextList = new ArrayList<>();
        // Stage stage = (Stage)(everything.getScene().getWindow());
        List<File> fileList =  fileChooser.showOpenMultipleDialog(stage);

        ArrayList<String> Selectednames = new ArrayList<>();

        if (fileList != null) {
            for(File file: fileList){
                String imagepath = file.getAbsolutePath();
           //     Image image = new Image(new FileInputStream(file));
                outputTextList = app.main(imagepath);

                tempMap.put(imagepath, outputTextList);
                ListviewOutput.put(imagepath,outputTextList);
            }
            addOuputListview(tempMap);
        }
    }

    public void addOuputListview(Map<String,List<String >> listMap){
        String currentFile ;
        List<String> selectedNames = new ArrayList<>();
        for (Map.Entry mapElement : listMap.entrySet()) {
            selectedNames.add((String)mapElement.getKey());

        }
        outputnamelist = FXCollections.observableArrayList(selectedNames);
        outputImageList.getItems().addAll(outputnamelist);

        outputImageList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                try {
                    String filename = (String) outputImageList.getSelectionModel().getSelectedItem();
                    setOutputVbox(ListviewOutput.get(filename));
                    selectedFromOutputlist = outputImageList.getSelectionModel().getSelectedItems();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    private void setuserdetails() {
        userName.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        phoneNumber.setText(currentUser.getPhone());
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

    public void LogOut(ActionEvent actionEvent) {
        currentUser.setIsset(false);
        navigate("WelcomeVbox",everything);
    }

    public void setOutputVbox(List<String> list1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("outputShow.fxml"));
        ScrollPane child;
        try {
            child = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewBorderPane.setCenter(child);
        child.setFitToWidth(true);
//        child.setContent(new Label("nahid"));
        Outputshow outputshowController = fxmlLoader.getController();
         outputshowController.addLabels(list1);
    }

    public void assignDoc(ActionEvent actionEvent) throws SQLException {
        int courseid = Integer.parseInt(courseID.getText());
        int studentid = Integer.parseInt(studentID.getText());
        List<String> address = new ArrayList<>();

        HashMap<String,List<String>> tempMap = new HashMap<>();
        List<String> outputTextList = new ArrayList<>();
        if(!selectedFromOutputlist.isEmpty()){
            for(String x : selectedFromOutputlist){
                List<String> banglaText = ListviewOutput.get(x);
                storetxtfile(ListviewOutput.get(x), x.substring(0, x.length() - 4));
                address.add(x.substring(0, x.length() - 4) + ".txt");
            }
            String finaladress = commaseparated(address);
            documentDao.addDocument(courseid,studentid,finaladress);
            selectedFromOutputlist = new ArrayList<>();
        }




        courseID.clear();
        studentID.clear();
    }
    private String commaseparated(List<String> address){
        System.out.println(String.join(",", address));
        return String.join(",", address);
    }
    private void storetxtfile(List<String> bangla, String filename){
        try {
            FileWriter myWriter = new FileWriter(filename+ ".txt");
            for(int i=0;i<bangla.size();i++) {
                myWriter.write(bangla.get(i));
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
