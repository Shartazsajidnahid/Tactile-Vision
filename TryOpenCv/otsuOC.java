package com.example.javafxtry;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class otsuthresh {
    public void toOtsu() throws IOException {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        String file ="I:\\Java Fx Try\\gauss.jpg";
        Mat src = Imgcodecs.imread(file, Imgcodecs.IMREAD_GRAYSCALE);
        //Creating an empty matrices to store the destination image.
        Mat dst = new Mat(src.rows(), src.cols(), src.type());
        //Applying simple threshold
        Imgproc.threshold(src, dst, 50, 255, Imgproc.THRESH_OTSU);
        //Converting matrix to JavaFX writable image
        Image img = HighGui.toBufferedImage(dst);

        File ouptut = new File("otsu.jpg");
        ImageIO.write((RenderedImage) img, "jpg", ouptut);

        /*WritableImage writableImage= SwingFXUtils.toFXImage((BufferedImage) img, null);
        //Setting the image view
        ImageView imageView = new ImageView(writableImage);
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);
        //Setting the Scene object
        Group root = new Group(imageView);
        Scene scene = new Scene(root, 595, 400);
        stage.setTitle("Otsu Threshold");
        stage.setScene(scene);
        stage.show();*/
    }
}




