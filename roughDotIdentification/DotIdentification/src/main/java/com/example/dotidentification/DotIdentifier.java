package com.example.dotidentification;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.*;

import java.io.File;
import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class DotIdentifier {
    public void imageRead(){
        try {
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
            File input = new File("J:\\Academics\\5th semester\\SPL\\DotIdentification\\test.jpg");
            BufferedImage image = ImageIO.read(input);

            //int[] data = ((DataBuffer) image.getRaster().getDataBuffer()).getData();
//            Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
//            mat.put(0, 0, data);
//
//            Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
//            Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
//
//            byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
//            mat1.get(0, 0, data1);
//            BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
//            image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
//
//            File ouptut = new File("grayscale.jpg");
//            ImageIO.write(image1, "jpg", ouptut);
            for(int i=0;i<image.getHeight();i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    int pixel = image.getRGB(i, j);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    System.out.println(i + " " + j);
                    System.out.println(red + " " + green + " " + blue);

                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
