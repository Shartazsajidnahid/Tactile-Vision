package com.example.demo;

import DotIdentifier.App;
import Management.CurrentUser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class TranslateVbox implements Initializable {
    @FXML
    private VBox outputVbox;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private VBox everything;
    @FXML
    private BorderPane viewBorderPane;

    @FXML
    private ListView inputImageList;
    @FXML
    private ListView outputImageList;

    private HashMap<String, List<String>> ListviewOutput;



    @FXML
    private ImageView showImage;

    private ObservableList<String> inputnamelist;
    private ObservableList<String> outputnamelist;

    private List<String> selectedFromInputlist;
    private List<String> selectedFromOutputlist;

    private FileChooser fileChooser;
    private App app;
    private CurrentUser currentUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("whatsup");
        selectedFromInputlist = new ArrayList<>();
        selectedFromOutputlist = new ArrayList<>();
        app = new App();
        inputImageList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        outputImageList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ListviewOutput = new HashMap<>();
        currentUser = CurrentUser.getInstance();
        fileChooser = new FileChooser();
    }

    public void addDynamicButton(ObservableList<String> names){
        String currentFile ;
        inputImageList.getItems().addAll(names);
        inputImageList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                try {
                    String filename = (String) inputImageList.getSelectionModel().getSelectedItem();
                    showimage(filename);
                    selectedFromInputlist = inputImageList.getSelectionModel().getSelectedItems();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addPhotos() throws MalformedURLException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        // Stage stage = (Stage)(everything.getScene().getWindow());
        List<File> fileList =  fileChooser.showOpenMultipleDialog(stage);

        ArrayList<String> Selectednames = new ArrayList<>();

        if (fileList != null) {
            for(File file: fileList){
                String imagepath = file.toURI().toURL().toString();
                Selectednames.add(file.getAbsolutePath());
            }
            inputnamelist = FXCollections.observableArrayList(Selectednames);
            addDynamicButton(inputnamelist);
        }
    }
    public void showimage(String file) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(file));

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("Imageview.fxml"));
        ScrollPane child;
        try {
            child = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewBorderPane.setCenter(child);
//        List<String> list1 = Arrays.asList("উন্মুক্ত করা বা খোলা","অভাব আছে এমন ","কোন উপায়ে বেঁচে থাকা ");
        ViewImage viewImage = fxmlLoader.getController();
        child.setFitToWidth(true);
        viewImage.setShowImage(image);

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

    public void Convert(ActionEvent actionEvent) throws IOException {

        HashMap<String,List<String>> tempMap = new HashMap<>();
        List<String> outputTextList = new ArrayList<>();
        if(!selectedFromInputlist.isEmpty()){
            for(String x : selectedFromInputlist){
                Image image = new Image(new FileInputStream(x));
                outputTextList = app.main(x);
                tempMap.put(x, outputTextList);
                ListviewOutput.put(x,outputTextList);
            }
            addOuputListview(tempMap);
//        changeCenterPane(viewBorderPane, "outputShow");
            //setOutputVbox(outputTextList);
//        addLabels(outputTextList);
            selectedFromInputlist = new ArrayList<>();
            System.out.println("Map sze : " + ListviewOutput.size());
        }
    }
    public void saveasTxt(ActionEvent actionEvent) {
        HashMap<String,List<String>> tempMap = new HashMap<>();
        List<String> outputTextList = new ArrayList<>();
        if(!selectedFromOutputlist.isEmpty()){
            for(String x : selectedFromOutputlist){
                List<String> banglaText = ListviewOutput.get(x);
                toSaveasText(banglaText);
            }
            selectedFromOutputlist = new ArrayList<>();

        }
    }

    private void toSaveasText(List<String> banglaText) {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        fileChooser.setInitialFileName("newfile");
        File file = fileChooser.showSaveDialog(new Stage());


        if(file != null){
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(file);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            for( String x : banglaText){
                printWriter.write(x);
                printWriter.write("\n");
            }
            printWriter.close();
        }
    }


    public void setOutputVbox( List<String> list1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("outputShow.fxml"));
        ScrollPane child;
        try {
            child = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewBorderPane.setCenter(child);
//        List<String> list1 = Arrays.asList("উন্মুক্ত করা বা খোলা","অভাব আছে এমন ","কোন উপায়ে বেঁচে থাকা ");
        Outputshow outputshowController = fxmlLoader.getController();
        child.setFitToWidth(true);
        outputshowController.addLabels(list1);
    }
    public void toWelcome(ActionEvent actionEvent){
        if(currentUser.isIsset()){
            changeWindow(currentUser.getUserType()+"Page");
        }
        else{
            changeWindow("WelcomeVbox");
        }

    }
    private void changeWindow(String pane) {
        Pane view = null;
        try {
            URL fileUrl = HelloApplication.class.getResource(pane+".fxml");
            if(fileUrl == null){
                throw new FileNotFoundException("fxml file not found");
            }
            view = new FXMLLoader().load(fileUrl);

        }catch (Exception e){
            System.out.println("file not found");
        }
        FXMLLoader login = new FXMLLoader();
        everything.getChildren().setAll(view);
    }

    private void changeCenterPane(BorderPane pane, String paneName){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource(paneName+".fxml"));
        ScrollPane child;
        try {
            child = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pane.setCenter(child);
    }
}