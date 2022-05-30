import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.*;

import java.io.File;
import java.nio.Buffer;
import javax.imageio.ImageIO;


public class ImageRead {
    public BufferedImage ImageRead2(){
        BufferedImage image=null;
        try {
            File input = new File("edit32.png");
            image = ImageIO.read(input);
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
