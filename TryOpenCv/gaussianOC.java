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

public class gaussianFilter {
    public void togauss() throws IOException {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        String file ="I:\\Java Fx Try\\grayscaled.jpg";
        Mat src = Imgcodecs.imread(file);
        //Creating destination matrix
        Mat dst = new Mat(src.rows(), src.cols(), src.type());
        //Applying GaussianBlur on the Image
        Imgproc.GaussianBlur(src, dst, new Size(15, 15), 0);
        //Converting matrix to JavaFX writable image
        Image img = HighGui.toBufferedImage(dst);
        File ouptut = new File("gauss.jpg");
        ImageIO.write((RenderedImage) img, "jpg", ouptut);
    }
}
