package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewImage {

    @FXML
    private ImageView showImage;

    public void setShowImage(Image image){
        showImage.setImage(image);
    }
}
