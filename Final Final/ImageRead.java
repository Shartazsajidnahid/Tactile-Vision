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
            File input = new File("images\\179s.png");
            image = ImageIO.read(input);
            return image;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }
}
