package DotIdentifier;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImageRead {
    public BufferedImage ImageRead2(String filename){
        BufferedImage image=null;
        try {
            File input = new File(filename);
            image = ImageIO.read(input);
            return image;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
