package Preprocessor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class grayscale {

    BufferedImage  image;
    int width;
    int height;

    public void convertToGrayscale(BufferedImage image) {

        try {
           // File input = new File("digital_image_processing.jpg");
           // image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            for(int i=0; i<height; i++) {

                for(int j=0; j<width; j++) {

                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed());
                    int green = (int)(c.getGreen());
                    int blue = (int)(c.getBlue());
                    int sum = red+green+blue;
                    sum/=3;
                    Color newColor = new Color(sum,sum,sum);

                    image.setRGB(j,i,newColor.getRGB());
                }
            }

            File ouptut = new File("grayscale1.jpg");
            ImageIO.write(image, "jpg", ouptut);

        } catch (Exception e) {
            System.out.println("some error occurred");
        }
    }

}
