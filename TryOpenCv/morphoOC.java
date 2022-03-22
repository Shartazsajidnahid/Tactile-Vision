package com.example.javafxtry;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class morpho {
    public void tomorpho() throws IOException {
        //Loading the OpenCV core library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Reading image data
        String file = "I:\\Java Fx Try\\otsu2.jpg";
        Mat src = Imgcodecs.imread(file);
        //Creating destination matrix
        Mat dst = new Mat(src.rows(), src.cols(), src.type());


        int erosion_size = 2;
        int dilation_size = 2;
        //erosion

        //Preparing the kernel matrix object
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(2*erosion_size + 1, 2*erosion_size+1));
        Imgproc.erode(src, dst, element);

        //Converting matrix to JavaFX writable image
        Image img = HighGui.toBufferedImage(dst);

        File ouptut = new File("erosion.jpg");
        ImageIO.write((RenderedImage) img, "jpg", ouptut);

        // dilation
        file = "I:\\Java Fx Try\\erosion.jpg";
        src = Imgcodecs.imread(file);
        dst = new Mat(src.rows(), src.cols(), src.type());
        Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(2*dilation_size + 1, 2*dilation_size+1));
        Imgproc.dilate(src, dst, element1);

        img = HighGui.toBufferedImage(dst);
        ouptut = new File("dilation.jpg");
        ImageIO.write((RenderedImage) img, "jpg", ouptut);




    }
}
