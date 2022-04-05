import org.opencv.core.Core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageReadWrite {
    public void ImageRead2(){
        BufferedImage image=null;
        try {
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
            File input = new File("imageSaved.jpg");
            image = ImageIO.read(input);
            for(int i=0;i<image.getWidth();i++){
                for(int j=0;j<image.getHeight();j++){
                    int d=image.getRGB(i,j);
                    Color clr= new Color(d);
                    //System.out.println(clr.getRed()+ " " +clr.getBlue() + " " + clr.getGreen());
                }
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
