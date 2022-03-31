package com.example.dotidentification;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.*;

import java.io.File;
import java.nio.Buffer;
import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class ImageRead {
    public BufferedImage ImageRead2(){
        BufferedImage image=null;
        try {
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
            File input = new File("J:\\Academics\\5th semester\\SPL\\DotIdentification\\test.jpg");
            image = ImageIO.read(input);
            System.out.println(image.getType());
            BufferedImage image1= new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            image1.getGraphics().drawImage(image, 0, 0, null);
            ImageIO.write(image,"jpg", new File("imageSaved1.jpg"));
            return image1;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
